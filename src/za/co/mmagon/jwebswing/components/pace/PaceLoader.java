/*
 * Copyright (C) 2017 Marc Magon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package za.co.mmagon.jwebswing.components.pace;

import za.co.mmagon.FileTemplates;
import za.co.mmagon.jwebswing.Feature;
import za.co.mmagon.jwebswing.base.html.interfaces.children.BodyFeatures;
import za.co.mmagon.jwebswing.base.servlets.interfaces.Loader;
import za.co.mmagon.jwebswing.components.pace.preloadedthemes.PaceTheme;
import za.co.mmagon.jwebswing.htmlbuilder.javascript.JavaScriptPart;
import za.co.mmagon.jwebswing.plugins.ComponentInformation;

/**
 * An implementation of Pace Loader
 * <p>
 *
 * @author Marc Magon
 * @version 1.0
 * @since 29 Aug 2015
 */
@ComponentInformation(name = "Pace",
		description = "Automatic page load progress bar",
		url = "http://github.hubspot.com/pace/docs/welcome/")
public class PaceLoader extends Feature<JavaScriptPart, PaceLoader>
		implements Loader, BodyFeatures, IPaceLoader
{

	private static final long serialVersionUID = 1L;
	/**
	 * The actual theme
	 */
	private PaceTheme theme;


	/**
	 * Creates a Pace Loader with a theme
	 *
	 * @param theme
	 */
	public PaceLoader(PaceTheme theme)
	{
		super("PaceLoader");
		this.theme = theme;
		FileTemplates.getTemplateVariables().put("//PACE_TRACK_START", new StringBuilder("Pace.track(function(){" + getNewLine()));
		FileTemplates.getTemplateVariables().put("//PACE_TRACK_END", new StringBuilder("});" + getNewLine()));
		addJavaScriptReference(PaceLoaderReferencePool.PaceLoader.getJavaScriptReference());
		getProperties().put(PaceLoaderConfigurator.PaceEnabled, true);
	}

	@Override
	protected void assignFunctionsToComponent()
	{
		//Nothing Needed
	}

	/**
	 * A nicer view
	 *
	 * @return
	 */
	public IPaceLoader asMe()
	{
		return this;
	}

	/**
	 * Gets the current pace loader theme
	 *
	 * @return
	 */
	@Override
	public PaceTheme getTheme()
	{
		return theme;
	}

	/**
	 * Sets the theme for this pace loader
	 *
	 * @param theme
	 */
	@Override
	public void setTheme(PaceTheme theme)
	{
		this.theme = theme;
	}

	@Override
	public void preConfigure()
	{
		if (!isConfigured())
		{
			addCssReference(theme.getCSSReference());
		}
		super.preConfigure();
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof PaceLoader))
		{
			return false;
		}
		if (!super.equals(o))
		{
			return false;
		}

		PaceLoader that = (PaceLoader) o;

		return getTheme() == that.getTheme();
	}

	@Override
	public int hashCode()
	{
		int result = super.hashCode();
		result = 31 * result + (getTheme() != null ? getTheme().hashCode() : 0);
		return result;
	}
}
