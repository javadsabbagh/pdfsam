/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 22 ott 2015
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
package org.pdfsam.community;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.pdfsam.PdfsamEdition;
import org.springframework.core.env.Environment;

public class PdfsamCommunityTest {
    @Test(expected = IllegalArgumentException.class)
    public void blankName() {
        new PdfsamCommunity(" ", "something", mock(Environment.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void blankShortName() {
        new PdfsamCommunity("Something", " ", mock(Environment.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullEnv() {
        new PdfsamCommunity("name", "short", null);
    }

    @Test
    public void edition() {
        assertEquals(PdfsamEdition.COMMUNITY, new PdfsamCommunity("name", "short", mock(Environment.class)).edition());
    }
}
