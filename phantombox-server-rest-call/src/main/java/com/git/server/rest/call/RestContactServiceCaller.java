package com.git.server.rest.call;

import com.git.domain.api.IContact;
import com.git.domain.api.IContactJsonWrapper;
import com.git.domain.impl.ContactJsonWrapper;
import com.git.server.rest.api.IRestContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;

/**
 * {@link IRestContactService} interface implementation.
 * <p/>
 * User: dmgcodevil
 * Date: 12/8/12
 * Time: 10:10 AM
 */
public class RestContactServiceCaller implements IRestContactService {

    public static final String FIND_CONTACT_BY_NAME = "/findContactByName";
    public static final String FIND_CONTACT_BY_EMAIL = "/findContactByEmail";
    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    private String service;

    private String server;

    private static final String CONTACT_REST_CALL = "/contact";

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
    public IContact findContactByName(String name) {
        StringBuilder urlBuilder = buildUrlPrefix();
        urlBuilder.append(CONTACT_REST_CALL).append(FIND_CONTACT_BY_NAME);
        addParameters(urlBuilder, "name");
        IContactJsonWrapper contactWrapper = restTemplate.getForObject(urlBuilder.toString(),
            ContactJsonWrapper.class, name);
        return contactWrapper.getContact();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IContact findContactByEmail(String email) {
        StringBuilder urlBuilder = buildUrlPrefix();
        urlBuilder.append(CONTACT_REST_CALL).append(FIND_CONTACT_BY_EMAIL);
        addParameters(urlBuilder, "email");
        IContactJsonWrapper contactWrapper = restTemplate.getForObject(urlBuilder.toString(),
            ContactJsonWrapper.class, email);
        return contactWrapper.getContact();
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
