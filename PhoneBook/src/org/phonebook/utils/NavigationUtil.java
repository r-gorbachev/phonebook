package org.phonebook.utils;

import java.io.IOException;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;

public class NavigationUtil {
	public static void doRedirect(String URL) {
		FacesContext context=FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect(URL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void navigate(String URL) {
		FacesContext context = FacesContext.getCurrentInstance();
		ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler)
		context.getApplication().getNavigationHandler();
		handler.performNavigation(URL);
	}

}
