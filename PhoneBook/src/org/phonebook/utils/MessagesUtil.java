package org.phonebook.utils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.phonebook.constants.ConstantsError;

public final class MessagesUtil {
	private static Map<String, ResourceBundle> resources;
	
	private MessagesUtil() {
	}
	static {
		resources = new HashMap<String, ResourceBundle>();
	}
	
	public static String getMessage(String resourceName, String keyName, Object[] params) {
		FacesContext context = FacesContext.getCurrentInstance();
		Locale locale = getLocale(context);
		String resourceKey = resourceName + locale.getISO3Language();
		
		ResourceBundle bundle = null;
		String message = null;
		try {
			synchronized(resources) {
				if(resources.containsKey(resourceKey)) {
					bundle = resources.get(resourceKey);
				} else {
					bundle = getBundle(resourceName, context);
					resources.put(resourceKey, bundle);
				}
			}
			if(bundle != null) {
				message = bundle.getString(keyName);
			}
		} catch(MissingResourceException e) {
			System.err.println(ConstantsError.RESOURCE + resourceName + " Key: " + keyName + "\n" + e);
		}  
		if(params != null && message != null) {
			MessageFormat formatter = new MessageFormat(message, locale);
			message = formatter.format(params);
		} 
		return message;
		
	}
	private static ResourceBundle getBundle(String resourceName, FacesContext context)  {
		ClassLoader loader = getLoader();
		Locale locale = getLocale(context);
		return ResourceBundle.getBundle(resourceName, locale, loader);		
	}
	
	private static Locale getLocale(FacesContext context) {
		Locale locale = null;
		UIViewRoot viewRoot = context.getViewRoot();
		if(viewRoot != null) {
			locale = viewRoot.getLocale();
		}
		if(locale == null) {
			locale = Locale.getDefault();
		}
		return locale;
	}
	
	private static ClassLoader getLoader() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if(loader == null) {
			loader = ClassLoader.getSystemClassLoader();
		}
		return loader;
	}

}
