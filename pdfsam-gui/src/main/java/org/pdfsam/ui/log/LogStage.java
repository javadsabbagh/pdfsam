/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 01/nov/2013
 * Copyright 2013 by Andrea Vacondio (andrea.vacondio@gmail.com).
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
package org.pdfsam.ui.log;

import static org.sejda.eventstudio.StaticStudio.eventStudio;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.pdfsam.configuration.StylesConfig;
import org.pdfsam.i18n.DefaultI18nContext;
import org.pdfsam.ui.commons.ClosePane;
import org.pdfsam.ui.commons.HideOnEscapeHandler;
import org.pdfsam.ui.commons.ShowStageRequest;
import org.pdfsam.ui.support.Style;
import org.sejda.eventstudio.annotation.EventListener;
import org.sejda.eventstudio.annotation.EventStation;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Stage for the log panel
 * 
 * @author Andrea Vacondio
 * 
 */
@Named
public class LogStage extends Stage {

    @EventStation
    public static final String LOGSTAGE_EVENTSTATION = "LogStage";

    @Inject
    public LogStage(LogPane logPane, LogListView logView, Collection<Image> logos, StylesConfig styles) {
        BorderPane containerPane = new BorderPane();
        containerPane.getStyleClass().addAll(Style.CONTAINER.css());
        containerPane.setCenter(logPane);
        containerPane.setBottom(new ClosePane());
        Scene scene = new Scene(containerPane);
        scene.getStylesheets().addAll(styles.styles());
        scene.setOnKeyReleased(new HideOnEscapeHandler(this));
        setScene(scene);
        setTitle(DefaultI18nContext.getInstance().i18n("Log register"));
        getIcons().addAll(logos);
        setMaximized(true);
        eventStudio().addAnnotatedListeners(this);
        showingProperty().addListener((v, oldVal, newVal) -> {
            if (newVal) {
                eventStudio().add(logView, LOGSTAGE_EVENTSTATION);
            } else {
                eventStudio().remove(logView, LOGSTAGE_EVENTSTATION);
            }
        });
    }

    @EventListener
    void requestShow(ShowStageRequest event) {
        if (!isShowing()) {
            centerOnScreen();
            show();
        }
        requestFocus();
    }
}
