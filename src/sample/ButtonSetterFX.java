package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

public class ButtonSetterFX implements StandardFX{
    /**
     * Class creates, sets and returns all buttons in the game
     */

    public ButtonSetterFX() {
    }

    public Button setButtonStates(String text){
        /**
         * returns normal buttons
         */
        Button button =  new Button();
        button.setStyle(buttonStyle);
        button.setText(text);
        button.setOnMouseEntered(e -> button.setStyle(buttonHoverStyle));
        button.setOnMouseExited(e -> button.setStyle(buttonStyle));
        button.setOnMousePressed(e -> button.setStyle(buttonPressedStyle));
        return button;
    }

    public ToggleButton setToggleButtonStates(String id){
        /**
         * Returns toggle buttons
         */
        ToggleButton toggleButton = new ToggleButton();
        toggleButton.setText(id);
        toggleButton.setId(id);
        toggleButton.setStyle(buttonStyle);
        toggleButton.setOnMousePressed(e -> toggleButton.setStyle(buttonPressedStyle));
        return toggleButton;
    }

    public void setOnMouseEnteredAction(ToggleButton toggleButton){
        if(!toggleButton.isSelected()) {
            toggleButton.setStyle(buttonHoverStyle);
        }
        else{
            toggleButton.setStyle(buttonPressedStyle);
        }
    }

    public void setOnMouseExitedAction(ToggleButton toggleButton){
        if(!toggleButton.isSelected()){
            toggleButton.setStyle(buttonStyle);
        }
        else{
            toggleButton.setStyle(buttonPressedStyle);
        }
    }

    public void setUnselected(ToggleButton toggleButton){
        toggleButton.setSelected(false);
        setOnMouseEnteredAction(toggleButton);
        setOnMouseExitedAction(toggleButton);
        toggleButton.setStyle(buttonHoverStyle);
    }

    public void setSelected(ToggleButton toggleButton){
        toggleButton.setSelected(true);
        toggleButton.setOnMouseEntered(null);
        toggleButton.setOnMouseExited(null);
        toggleButton.setStyle(buttonPressedStyle);
    }
}
