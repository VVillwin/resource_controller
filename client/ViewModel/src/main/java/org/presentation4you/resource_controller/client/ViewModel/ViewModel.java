package org.presentation4you.resource_controller.client.ViewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.presentation4you.resource_controller.client.Authentication.Authentication;
import org.presentation4you.resource_controller.client.RootDataManagement.DataManagement;
import org.presentation4you.resource_controller.client.RootDataManagement.IDataManagement;
import org.presentation4you.resource_controller.commons.Request.*;
import org.presentation4you.resource_controller.commons.RequestsFields.RequestsFields;
import org.presentation4you.resource_controller.commons.RequestsFields.ResourcesFields;
import org.presentation4you.resource_controller.commons.Response.GetRequestsResp;
import org.presentation4you.resource_controller.commons.Response.GetResourcesResp;
import org.presentation4you.resource_controller.commons.Response.IResponse;
import org.presentation4you.resource_controller.commons.Response.ResponseStatus;
import org.presentation4you.resource_controller.commons.Role.IRole;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewModel {
    private final StringProperty txtResourceId = new SimpleStringProperty();
    private final StringProperty txtResourceType = new SimpleStringProperty();
    private final StringProperty txtRequestId = new SimpleStringProperty();
    private final StringProperty txtResIdForReq = new SimpleStringProperty();
    private final StringProperty txtResTypeForReq = new SimpleStringProperty();
    private final StringProperty txtFrom = new SimpleStringProperty();
    private final StringProperty txtTo = new SimpleStringProperty();
    private final StringProperty resourcesTable = new SimpleStringProperty();
    private final StringProperty requestsTable = new SimpleStringProperty();

    private final IDataManagement dm = new DataManagement();
    private IRole role;
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public ViewModel() {
        role = Authentication.getInstance().getRole();
        txtResourceId.set("");
        txtResourceType.set("");
        txtRequestId.set("");
        txtResIdForReq.set("");
        txtResTypeForReq.set("");

        resourcesTable.set("");
        requestsTable.set("");

        Date now = Calendar.getInstance().getTime();
        String date = formatter.format((now));
        txtFrom.set(date);
        txtTo.set(date);
    }

    public void addResource() {
        Integer id = Integer.parseInt(getTxtResourceId());
        String type = getTxtResourceType();
        IRequest request = new AddResourceReq(role,
                id, type);
        dm.send(request);
    }

    public void removeResource() {
        Integer id = Integer.parseInt(getTxtResourceId());
        IRequest request = new RemoveResourceReq(role, id);
        dm.send(request);
    }

    public void getResources() {
        Integer id;
        String type = null;

        if (getTxtResourceId().equals("")) {
            id = 0;
        } else {
            id = Integer.parseInt(getTxtResourceId());
        }
        if (!getTxtResourceType().equals("")) {
            type = getTxtResourceType();
        }
        IRequest request = new GetResourcesReq(role, id, type);

        IResponse response = dm.send(request);
        System.out.println(response.getStatus());
        if (response instanceof GetResourcesResp) {
            if (response.getStatus() == ResponseStatus.OK) {
                List<ResourcesFields> resources = ((GetResourcesResp) response).getResources();

                List<String> resourcesList = new ArrayList<>();
                for(ResourcesFields res : resources) {
                    resourcesList.add(String.format("%d %s\n", res.getId(), res.getType()));
                }
                String resourcesToView = "";
                for (String s : resourcesList) {
                    resourcesToView += s;
                }
                resourcesTable.set(resourcesToView);
            }
        }
    }

    public void approveRequest() {
        Integer id = 0;

        id = getIntFromString(getTxtRequestId());

        IRequest request = new UpdateRequestReq(role, id, true);

        dm.send(request);
    }

    public void getRequests() {
        RequestsFields match = new RequestsFields();
        getRequests(match);
    }

    public void getPendingRequests() {
        RequestsFields match = new RequestsFields().setIsApproved(false);
        getRequests(match);
    }

    public final String getTxtResourceId() {
        return txtResourceId.get();
    }

    public final String getTxtResourceType() {
        return txtResourceType.get();
    }

    public final String getTxtRequestId() {
        return txtRequestId.get();
    }

    public final String getTxtResIdForReq() {
        return txtResIdForReq.get();
    }

    public final String getTxtResTypeForReq() {
        return txtResTypeForReq.get();
    }

    public final String getTxtFrom() {
        return txtFrom.get();
    }

    public final String getTxtTo() {
        return txtTo.get();
    }

    public StringProperty txtRequestIdProperty() {
        return txtRequestId;
    }

    public StringProperty txtResIdForReqProperty() {
        return txtResIdForReq;
    }

    public StringProperty txtResTypeForReqProperty() {
        return txtResTypeForReq;
    }

    public StringProperty txtFromProperty() {
        return txtFrom;
    }

    public StringProperty txtToProperty() {
        return txtTo;
    }

    public StringProperty txtResourceIdProperty() {
        return txtResourceId;
    }

    public StringProperty txtResourceTypeProperty() {
        return txtResourceType;
    }

    public StringProperty resourcesTableProperty() {
        return resourcesTable;
    }

    public final String getResourcesTable() {
        return resourcesTable.get();
    }

    public StringProperty requestsTableProperty() {
        return requestsTable;
    }

    public final String getRequestsTable() {
        return requestsTable.get();
    }

    private Integer getIntFromString(final String str) {
        if (str.equals("")) {
            return 0;
        } else {
            return Integer.parseInt(str);
        }
    }

    private Calendar getCalendar(String requiredDate) {
        Date date;
        try {
            date = formatter.parse(requiredDate);
        } catch (ParseException pe) {
            pe.printStackTrace();
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private void getRequests(RequestsFields match) {
        if (!getTxtRequestId().equals("")) {
            match.setId(Integer.parseInt(getTxtRequestId()));
        }

        if (!getTxtResIdForReq().equals("")) {
            match.setResourceId(Integer.parseInt(getTxtResIdForReq()));
        }

        if (!getTxtResTypeForReq().equals("")) {
            match.setResourceType(getTxtResTypeForReq());
        }

        if (!getTxtFrom().equals("")) {
            match.setFrom(getCalendar(getTxtFrom()));
        }
        if (!getTxtTo().equals("")) {
            match.setTo(getCalendar(getTxtTo()));
        }

        IRequest request = new GetRequestsReq(role, match);

        IResponse response = dm.send(request);
        if (response instanceof GetRequestsResp) {
            if (response.getStatus() == ResponseStatus.OK) {
                List<RequestsFields> requests = ((GetRequestsResp) response).getRequests();

                List<String> requestsList = new ArrayList<>();
                for(RequestsFields req : requests) {
                    requestsList.add(String.format("%d %s %d %s %s %s %b\n", req.getId(), req.getLogin(),
                            req.getResourceId(), req.getResourceType(), formatter.format(req.getFrom().getTime()),
                            formatter.format(req.getTo().getTime()), req.getIsApproved()));
                }
                String requestsToView = "";
                for (String s : requestsList) {
                    requestsToView += s;
                }
                requestsTable.set(requestsToView);
            }
        }
    }
}
