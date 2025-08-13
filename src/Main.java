import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ControllerManager controllers = new ControllerManager();
        controllers.initSDLGamepad();
        // Map to store which button is assigned to which action
        HashMap<String, String> buttonMappings = new HashMap<>();

        System.out.println(
                "Welcome to this scuffed combo visualizer. To start, press the button you want to assign to P.");
        // Eventually, I plan to let the user choose here. Right now its easier to get it set up linearly.
        // System.out.println("Welcome to this scuffed combo visualizer. If you want to assign all buttons linearly, type 1. If you want to assign buttons individually, type 2.");
        buttonAssigner(controllers, buttonMappings, "P");
        System.out.println("Button you want to assign to K.");
        buttonAssigner(controllers, buttonMappings, "K");
        System.out.println("Button you want to assign to S.");
        buttonAssigner(controllers, buttonMappings, "S");
         System.out.println("Button you want to assign to HS.");
        buttonAssigner(controllers, buttonMappings, "HS");


        // You can print the mapping to verify
        System.out.println("Button mappings: " + buttonMappings);
        controllers.quitSDLGamepad();
    }

    // What we call to detect a button for assignment. 
    public static void buttonAssigner(ControllerManager controllers, HashMap<String, String> buttonMappings, String button) {
        boolean buttonPressed = false;
        ControllerState prevState = controllers.getState(0);
        while (!buttonPressed) {
            controllers.update(); // look for input
            ControllerState state = controllers.getState(0); // always assume the right controller is 0 for now

            String pressedButton = getNewlyPressedButton(state, prevState);
            if (pressedButton != null) {
                System.out.println(pressedButton.toUpperCase() + " button pressed!");
                buttonMappings.put(button, pressedButton);
                buttonPressed = true;
            }
            prevState = state;
        }
    }

    // Returns the name of the button that was newly pressed (not held)
    public static String getNewlyPressedButton(ControllerState curr, ControllerState prev) {
        if (curr.a && !prev.a) return "a";
        if (curr.b && !prev.b) return "b";
        if (curr.x && !prev.x) return "x";
        if (curr.y && !prev.y) return "y";
        if (curr.lb && !prev.lb) return "lb";
        if (curr.rb && !prev.rb) return "rb";
        if (curr.back && !prev.back) return "back";
        if (curr.start && !prev.start) return "start";
        if (curr.dpadUp && !prev.dpadUp) return "dpadUp";
        if (curr.dpadDown && !prev.dpadDown) return "dpadDown";
        if (curr.dpadLeft && !prev.dpadLeft) return "dpadLeft";
        if (curr.dpadRight && !prev.dpadRight) return "dpadRight";
        if (curr.leftStickClick && !prev.leftStickClick) return "leftStickClick";
        if (curr.rightStickClick && !prev.rightStickClick) return "rightStickClick";
        if (curr.leftTrigger > 0.5f && prev.leftTrigger <= 0.5f) return "leftTrigger";
        if (curr.rightTrigger > 0.5f && prev.rightTrigger <= 0.5f) return "rightTrigger";
        return null;
    }

    // lazy method so i dont have to do this every time
    public static String getPressedButton(ControllerState state) {
        if (state.a)
            return "a";
        if (state.b)
            return "b";
        if (state.x)
            return "x";
        if (state.y)
            return "y";
        if (state.lb)
            return "lb";
        if (state.rb)
            return "rb";
        if (state.back)
            return "back";
        if (state.start)
            return "start";
        if (state.dpadUp)
            return "dpadUp";
        if (state.dpadDown)
            return "dpadDown";
        if (state.dpadLeft)
            return "dpadLeft";
        if (state.dpadRight)
            return "dpadRight";
        if (state.leftStickClick)
            return "leftStickClick";
        if (state.rightStickClick)
            return "rightStickClick";
        if (state.leftTrigger > 0.5f)
            return "leftTrigger";
        if (state.rightTrigger > 0.5f)
            return "rightTrigger";
        return null;
    }
}

// // Print a message when the "A" button is pressed. Exit if the "B" button is
// // pressed
// // or the controller disconnects.
// // Taken from the jamepad example. Not mine!
// while (true) {
// ControllerState currState = controllers.getState(0);

// if (!currState.isConnected || currState.b) {
// break;
// }
// if (currState.a) {
// System.out.println("\"A\" on \"" + currState.controllerType + "\" is
// pressed");
// }
// }
// controllers.quitSDLGamepad();
// }
// }
