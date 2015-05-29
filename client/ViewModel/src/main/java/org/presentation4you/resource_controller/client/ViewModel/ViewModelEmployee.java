package org.presentation4you.resource_controller.client.ViewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewModelEmployee {
    private final StringProperty txtResourceId = new SimpleStringProperty();
    private final StringProperty txtResourceType = new SimpleStringProperty();
    private final StringProperty txtRequestId = new SimpleStringProperty();
    private final StringProperty txtResIdForReq = new SimpleStringProperty();
    private final StringProperty txtResTypeForReq = new SimpleStringProperty();
    private final StringProperty txtFrom = new SimpleStringProperty();
    private final StringProperty txtTo = new SimpleStringProperty();
    private final ObjectProperty<ObservableList<ResourcesFields>> tblResources =
            new SimpleObjectProperty<>(FXCollections.observableArrayList());

    private final IDataManagement dm = new DataManagement();
    private IRole role;
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public ViewModelEmployee() {
        role = Authentication.getInstance().getRole();
        txtResourceId.set("");
        txtResourceType.set("");
        txtRequestId.set("");
        txtResIdForReq.set("");
        txtResTypeForReq.set("");

        Date now = Calendar.getInstance().getTime();
        String date = formatter.format((now));
        txtFrom.set(date);
        txtTo.set(date);
    }

    public void getResources() {
        Integer id;
        String type = null;

        id = getIntFromString(getTxtResourceId());

        if (!getTxtResourceType().equals("")) {
            type = getTxtResourceType();
        }
        IRequest request = new GetResourcesReq(role, id, type);

        IResponse response = dm.send(request);
        if (response instanceof GetResourcesResp) {
            if (response.getStatus() == ResponseStatus.OK) {
                List<ResourcesFields> resources = ((GetResourcesResp) response).getResources();
                for(ResourcesFields res : resources) {
                    System.out.println(res.getId() + " " + res.getType());
                }
                tblResources.set(FXCollections.observableList(resources));
            }
        }
    }

    public void addRequest() {
        Integer resId = 0;

        resId = getIntFromString(getTxtResIdForReq());

        Calendar from = getCalendar(getTxtFrom());
        Calendar to = getCalendar(getTxtTo());

        IRequest request = new AddRequestReq(role, resId, from, to);

        dm.send(request);
    }

    public void removeRequest() {
        Integer id = 0;

        id = getIntFromString(getTxtRequestId());

        IRequest request = new RemoveRequestReq(role, id);

        dm.send(request);
    }

    public void getRequests() {
        RequestsFields match = new RequestsFields();
        getRequests(match);
    }

    public void getOwnRequests() {
        RequestsFields match = new RequestsFields().setLogin(role.getLogin());
        getRequests(match);
    }

    public final String getTxtResourceId() {
        return txtResourceId.get();
    }

    public final String getTxtResourceType() {
        return txtResourceType.get();
    }

    public ObservableList<ResourcesFields> getTblResources(){
        return tblResources.get();
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

    public StringProperty txtResourceIdProperty() {
        return txtResourceId;
    }

    public StringProperty txtResourceTypeProperty() {
        return txtResourceType;
    }

    public ObjectProperty<ObservableList<ResourcesFields>> tblGetResourcesProperty() {
        return tblResources;
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
                for(RequestsFields req : requests) {
                    System.out.println(req.getId() + " " + req.getLogin() + " " + req.getResourceId()
                            + " " + req.getResourceType() + " " + formatter.format(req.getFrom().getTime())
                            + " " + formatter.format(req.getTo().getTime())+ " " + req.getIsApproved());
                }
            }
        }
    }

}
