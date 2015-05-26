package org.presentation4you.resource_controller.server.Repository;

import org.presentation4you.resource_controller.commons.RequestsFields.RequestsFields;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

public class IntegrationTestsRepository implements IResourceRepo, IUserRepo, IRequestRepo {
    private Connection con = null;
    private Statement st = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    private String url = "jdbc:mysql://localhost:3306/test";
    final private String user = "pfu";
    final private String password = "pass";

    public IntegrationTestsRepository() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean hasResource(int id) {
        boolean ret = false;
        try {
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("SELECT COUNT(resId) FROM resources WHERE" +
                    " resId=?;");
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    ret = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return ret;
    }

    @Override
    public void addResource(int id, int typeId) {
        try {
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("INSERT INTO `resources`(resId, typeId) " +
                    "VALUES(?, ?);");

            pst.setInt(1, id);
            pst.setInt(2, typeId);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void removeResource(int id) {
        try {
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("DELETE FROM `resources` WHERE resId=?;");

            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public int getResourceType(String type) throws NoSuchElementException {
        int typeId = 0;
        try {
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("SELECT typeId FROM resource_types WHERE resource_type=?;");
            pst.setString(1, type);

            rs = pst.executeQuery();

            if (rs.next()) {
                typeId = rs.getInt(1);
            } else {
                throw new NoSuchElementException();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return typeId;
    }

    public void deleteAll() {
        try {
            con = DriverManager.getConnection(url, user, password);

            con.setAutoCommit(false);
            st = con.createStatement();

            st.addBatch("DELETE FROM resources");
            st.addBatch("DELETE FROM requests");

            st.executeBatch();
            con.commit();
        } catch (SQLException ex) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {
                    System.out.println(ex1.getMessage());
                }
            }
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public String getEmail(String login) {
        String email = null;
        try {
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("SELECT email FROM users WHERE login=?;");
            pst.setString(1, login);

            rs = pst.executeQuery();

            if (rs.next()) {
                email = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return email;
    }

    @Override
    public String authorize(String login, String pass) {
        String group = null;
        try {
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("SELECT g.`group` FROM `groups` g, `users` u " +
                    "WHERE u.`groupId`=g.`gId` AND u.`login`=? AND u.`pass`=?;");
            pst.setString(1, login);
            pst.setString(2, pass);

            rs = pst.executeQuery();

            if (rs.next()) {
                group = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return group;
    }

    @Override
    public boolean canAddRequest(int resourceId, Calendar from, Calendar to, String login) throws IllegalArgumentException {
        boolean ret = false;
        try {
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("SELECT COUNT(reqId) FROM requests r WHERE" +
                    " r.resId=? AND isApproved=1 AND (r.`from` BETWEEN ? and ? OR r.`to`" +
                    " BETWEEN ? and ?) AND r.uId<>(SELECT u.uId FROM users u WHERE u.login=?);");
            pst.setInt(1, resourceId);
            pst.setTimestamp(2, new Timestamp(from.getTimeInMillis()));
            pst.setTimestamp(3, new Timestamp(to.getTimeInMillis()));
            pst.setTimestamp(4, new Timestamp(from.getTimeInMillis()));
            pst.setTimestamp(5, new Timestamp(to.getTimeInMillis()));
            pst.setString(6, login);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    throw new IllegalArgumentException();
                }
            }

            pst = con.prepareStatement("SELECT COUNT(reqId) FROM requests r WHERE" +
                    " r.resId=? AND isApproved=1 AND (r.`from` BETWEEN ? and ? OR r.`to`" +
                    " BETWEEN ? and ?) AND r.uId=(SELECT u.uId FROM users u WHERE u.login=?);");
            pst.setInt(1, resourceId);
            pst.setTimestamp(2, new Timestamp(from.getTimeInMillis()));
            pst.setTimestamp(3, new Timestamp(to.getTimeInMillis()));
            pst.setTimestamp(4, new Timestamp(from.getTimeInMillis()));
            pst.setTimestamp(5, new Timestamp(to.getTimeInMillis()));
            pst.setString(6, login);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getInt(1) == 0) {
                    ret = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return ret;
    }

    @Override
    public int addRequest(int resourceId, Calendar from, Calendar to, String login) {
        int id = 0;
        try {
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("INSERT INTO `requests`(resId, `uId`, `from`, `to`) " +
                    "SELECT DISTINCT ?, u.uId, ?, ? FROM `users` u WHERE u.`login`=?;");

            pst.setInt(1, resourceId);
            pst.setTimestamp(2, new Timestamp(from.getTimeInMillis()));
            pst.setTimestamp(3, new Timestamp(to.getTimeInMillis()));
            pst.setString(4, login);
            pst.executeUpdate();

            pst = con.prepareStatement("SELECT reqId FROM requests r WHERE" +
                    " r.resId=? AND r.`from`=? AND r.`to`=?;");
            pst.setInt(1, resourceId);
            pst.setTimestamp(2, new Timestamp(from.getTimeInMillis()));
            pst.setTimestamp(3, new Timestamp(to.getTimeInMillis()));
            rs = pst.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return id;
    }

    @Override
    public boolean hasRequest(int id) {
        boolean ret = false;
        try {
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("SELECT COUNT(reqId) FROM requests WHERE reqId=?;");
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    ret = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return ret;
    }

    @Override
    public void removeRequest(int id) {
        try {
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("DELETE FROM `requests` WHERE reqId=?;");

            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public String getRequestOwner(int id) {
        String login = null;
        try {
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("SELECT u.login FROM requests r, users u WHERE " +
                    "u.uId=r.uId AND r.reqId=?;");
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                login = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return login;
    }

    @Override
    public void updateRequest(int id, boolean isApproved) {
        try {
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("UPDATE `requests` SET isApproved=? WHERE reqId=?;");

            pst.setBoolean(1, isApproved);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public List<RequestsFields> getRequests(RequestsFields match) {
        List<RequestsFields> requests = new ArrayList<>();
        try {
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement(prepareTemplateForStatement(match));
            fillInTemplate(match);
            rs = pst.executeQuery();

            while (rs.next()) {
                RequestsFields gotRequest = new RequestsFields();
                gotRequest.setId(rs.getInt(1));
                gotRequest.setLogin(rs.getString(2));
                gotRequest.setFrom(rs.getTimestamp(3));
                gotRequest.setTo(rs.getTimestamp(4));
                gotRequest.setResourceId(rs.getInt(5));
                gotRequest.setResourceType(rs.getString(6));
                gotRequest.setIsApproved(rs.getBoolean(7));
                requests.add(gotRequest);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return requests;
    }

    private void fillInTemplate(RequestsFields match) throws SQLException {
        int column = 1;
        if (match.getResourceId() != 0) {
            pst.setInt(column++, match.getResourceId());
        }
        if (match.getResourceType() != null) {
            pst.setString(column++, match.getResourceType());
        }
        if (match.getFrom() != null) {
            pst.setTimestamp(column++, new Timestamp(match.getFrom().getTimeInMillis()));
        }
        if (match.getTo() != null) {
            pst.setTimestamp(column++, new Timestamp(match.getTo().getTimeInMillis()));
        }
        if (match.lookForIsApproved() == true) {
            pst.setBoolean(column++, match.getIsApproved());
        }
        if (match.getLogin() != null) {
            pst.setString(column++, match.getLogin());
        }
    }

    private String prepareTemplateForStatement(RequestsFields match) {
        String st = "SELECT rq.reqId, u.login, rq.`from`, rq.`to`, rq.resId, " +
                "rt.resource_type, rq.isApproved FROM requests rq, resources re, users u, " +
                "resource_types rt WHERE rq.resId=re.resId AND u.uId=rq.uId AND re.typeId=rt.typeId" +
                "";
        if (match.getResourceId() != 0) {
            st += " AND rq.resId=?";
        }
        if (match.getResourceType() != null) {
            st += " AND rt.resource_type=?";
        }
        if (match.getFrom() != null) {
            st += " AND rq.`from`=?";
        }
        if (match.getTo() != null) {
            st += " AND rq.`to`=?";
        }
        if (match.lookForIsApproved()) {
            st += " AND rq.isApproved=?";
        }
        if (match.getLogin() != null) {
            st += " AND u.login=?";
        }

        st += ";";
        return st;
    }
}
