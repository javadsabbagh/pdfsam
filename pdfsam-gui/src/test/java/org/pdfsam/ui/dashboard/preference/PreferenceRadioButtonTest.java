/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 29/ago/2014
 * Copyright 2013-2014 by Andrea Vacondio (andrea.vacondio@gmail.com).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pdfsam.ui.dashboard.preference;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import javafx.scene.Parent;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.categories.TestFX;
import org.pdfsam.context.BooleanUserPreference;
import org.pdfsam.context.UserContext;

/**
 * @author Andrea Vacondio
 *
 */
@Category(TestFX.class)
public class PreferenceRadioButtonTest extends GuiTest {

    private UserContext userContext = mock(UserContext.class);

    @Override
    protected Parent getRootNode() {
        return new PreferenceRadioButton(BooleanUserPreference.SMART_OUTPUT, "select", false, userContext);
    }

    @Test
    public void preferenceSetOnClick() {
        click("select");
        verify(userContext).setBooleanPreference(eq(BooleanUserPreference.SMART_OUTPUT), eq(Boolean.TRUE));
    }
}
