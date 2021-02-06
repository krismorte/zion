package com.krismorte.zion.view;

import org.fest.swing.finder.JOptionPaneFinder;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JOptionPaneFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class CadastroDeServidorTest {

    DialogFixture demo;
    ResourceBundle resourceBundle;
    @BeforeEach
    void setUp() {
        resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
        demo = new DialogFixture(new CadastroDeServidor(null,false));
    }
    @Test
    void uiTest(){
        assertAll("labels",
                () -> assertEquals(resourceBundle.getString("PORT")+":",demo.label("lbPort").text()),
                () -> assertEquals(resourceBundle.getString("SERVER")+":",demo.label("lbServer").text()),
                () -> assertEquals(resourceBundle.getString("TYPE")+":",demo.label("lbType").text()),
                () -> assertEquals(resourceBundle.getString("USER")+":",demo.label("lbUser").text()),
                () -> assertEquals(resourceBundle.getString("PASS")+":",demo.label("lbPass").text()),
                () -> assertEquals(resourceBundle.getString("VERSION")+":",demo.label("lbVersion").text()),
                () -> assertEquals(resourceBundle.getString("DESCRIPTION")+":",demo.label("lbDescription").text())


        );

        assertAll("buttons",
                () -> assertEquals(resourceBundle.getString("TEST_CONNECTION"),demo.button("btnTestConn").text()),
                () -> assertEquals(resourceBundle.getString("SAVE"),demo.button("btnSave").text()),
                () -> assertEquals(Boolean.FALSE,demo.button("btnSave").target.isEnabled())
        );


        demo.button("btnTestConn").click();
        JOptionPaneFixture optionPane = demo.optionPane();
        assertNotNull(optionPane);
        optionPane.button().click();
    }

}