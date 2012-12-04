package com.git.server.rest.call;

import static com.git.domain.api.Constants.RESULT;
import com.git.domain.api.IUser;
import com.git.domain.api.IUserJsonWrapper;
import com.git.domain.impl.UserJsonWrapper;
import com.git.server.rest.api.IRestUserService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Rest caller.
 * <p/>
 * User: dmgcodevil
 * Date: 12/1/12
 * Time: 5:59 AM
 */
public class RestUserServiceCaller implements IRestUserService {


    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    private String service;

    private String server;

    private static final String USER_REST_CALL = "/user";

    private static final String USER_LOGIN_REST_CALL = "/login";

    private static final String USER_LOGOUT_REST_CALL = "/logout";

    private static final String USER_ADD_CONTACT_BY_NAME_REST_CALL = "/addContactByName";

    private static final String USER_ADD_CONTACT_BY_EMAIL_REST_CALL = "/addContactByEmail";

    private static final String USER_REMOVE_CONTACT_REST_CALL = "/removeContact";

    private static final String SIGN_Q = "?";

    private static final String SIGN_A = "&";

    /**
     * Gets service.
     *
     * @return service
     */
    public String getService() {
        return service;
    }

    /**
     * Set service.
     *
     * @param service service
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * Gets server.
     *
     * @return server
     */
    public String getServer() {
        return server;
    }

    /**
     * Sets server.
     *
     * @param server server
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser login(String name, String password, String ipAddress) {
        StringBuilder urlBuilder = buildUrlPrefix();
        urlBuilder.append(USER_REST_CALL).append(USER_LOGIN_REST_CALL);
        addParameters(urlBuilder, "name", "password", "ipAddress");
        IUserJsonWrapper userWrapper = restTemplate.getForObject(urlBuilder.toString(),
            UserJsonWrapper.class, name, password, ipAddress);
        return userWrapper.getUser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean logout(String name, String password) {
        boolean result = false;
        StringBuilder urlBuilder = buildUrlPrefix();
        urlBuilder.append(USER_REST_CALL).append(USER_LOGOUT_REST_CALL);
        addParameters(urlBuilder, "name", "password");
        Map<String, Boolean> response = restTemplate.getForObject(urlBuilder.toString(),
            HashMap.class, name, password);
        if (MapUtils.isNotEmpty(response)) {
            result = response.get(RESULT);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser addContactByUserName(String name, String password, String userName) {
        StringBuilder urlBuilder = buildUrlPrefix();
        urlBuilder.append(USER_REST_CALL).append(USER_ADD_CONTACT_BY_NAME_REST_CALL);
        addParameters(urlBuilder, "name", "password", "userName");
        IUserJsonWrapper userWrapper = restTemplate.getForObject(urlBuilder.toString(),
            UserJsonWrapper.class, name, password, userName);
        return userWrapper.getUser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser addContactByContactEmail(String name, String password, String email) {
        StringBuilder urlBuilder = buildUrlPrefix();
        urlBuilder.append(USER_REST_CALL).append(USER_ADD_CONTACT_BY_EMAIL_REST_CALL);
        addParameters(urlBuilder, "name", "password", "email");
        IUserJsonWrapper userWrapper = restTemplate.getForObject(urlBuilder.toString(),
            UserJsonWrapper.class, name, password, email);
        return userWrapper.getUser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeContactById(String name, String password, String contactId) {
        boolean result = false;
        StringBuilder urlBuilder = buildUrlPrefix();
        urlBuilder.append(USER_REST_CALL).append(USER_REMOVE_CONTACT_REST_CALL);
        addParameters(urlBuilder, "name", "password", "contactId");
        Map<String, Boolean> response = restTemplate.getForObject(urlBuilder.toString(),
            HashMap.class, name, password, contactId);
        if (MapUtils.isNotEmpty(response)) {
            result = response.get(RESULT);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkUserExists(String name, String password) {
        throw new UnsupportedOperationException();
    }

    private StringBuilder buildUrlPrefix() {
        StringBuilder urlBuilder = new StringBuilder(server);
        urlBuilder.append(service);
        return urlBuilder;
    }

    private void addParameters(StringBuilder urlBuilder, String... params) {
        int firstElement = 0;
        if (urlBuilder != null && params != null) {
            int size = params.length;
            for (int i = 0; i < size; i++) {
                if (i != firstElement) {
                    addParameter(urlBuilder, SIGN_A, params[i]);
                } else {
                    addParameter(urlBuilder, SIGN_Q, params[i]);
                }
            }
        }

    }

    private void addParameter(StringBuilder urlBuilder, String sign, String param) {
        urlBuilder.append(sign).append(param).append("={").append(param).append("}");
    }
}
