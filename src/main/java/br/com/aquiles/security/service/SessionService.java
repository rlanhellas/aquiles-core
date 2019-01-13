package br.com.aquiles.security.service;

import br.com.aquiles.security.dto.Subject;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/*
 * Store informations about session
 */
@Named("sessionService")
@SessionScoped
public class SessionService implements Serializable {

    //COMMON PROPS
    public static final String SUBJECT = "subject";

    private Map<String, Object> props = new HashMap<>();

    public Object getProp(String key) {
        return props.get(key);
    }

    public void addProp(String key, Object o) {
        props.put(key, o);
    }

    public Subject getSubject(){
        return (Subject) getProp(SUBJECT);
    }
}
