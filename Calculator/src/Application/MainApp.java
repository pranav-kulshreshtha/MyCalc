package Application;

import java.util.HashMap;
import java.math.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;


public class MainApp extends Application {
		private int state;
		private String expression;
		private String input;
		private String operation;
		private double inputA;
		private double inputB;
		private Label screen;
		private Label expScreen;
		private int mode;
		private Stage mainStage;
		private double result;
		HashMap<String, Button> buttons;
		
		public MainApp() {
			this.state = 0;
			this.expression = "";
			this.input = "0";
			this.operation = "+";
			this.inputA = 0;
			this.inputB = 0;
			this.result = 0;
			this.mode = 0;
			this.buttons = new HashMap<>();
		}
		
		//method to reset variables to initial state
		private void reset() {
			state = 0;
			expression = "";
			input = "0";
			operation = "+";
			inputA = 0;
			inputB = 0;
			result = 0;
			screen.setText(input);
			expScreen.setText(expression);
		}
		
		//helper method to calculate factorial of a given number
		private double factorial(double x) {
			if( x > 20 ) {
				return Double.MAX_VALUE;
			}
			if(x == 1) {
				return 1;
			}
			
			return x * factorial(x-1);
		}
		
		//helper method to set alignment and spacing between nodes in a gridpane in a scene
		private void setGridAlignment(GridPane grid) {
			grid.setVgap(2);
			grid.setHgap(10);
			grid.setHalignment(buttons.get("sci"), HPos.RIGHT);
			grid.setHalignment(buttons.get("divide"), HPos.RIGHT);
			grid.setHalignment(buttons.get("multiply"), HPos.RIGHT);
			grid.setHalignment(buttons.get("minus"), HPos.RIGHT);
			grid.setHalignment(buttons.get("plus"), HPos.RIGHT);
		}
		
		//helper method to create the basic layout for any scene
		private StackPane buildBasicLayout(double width) {
			Rectangle rect = new Rectangle();
			rect.setHeight(100);
			rect.setWidth(width);
			
			//creating a screen that displays current input
			screen = new Label(input);
			screen.setTextFill(Color.WHITE);
			screen.setFont(new Font("Britannic Bold", 28) );
			
			//creating a screen that displays the resultant expression
			expScreen = new Label(expression);
			expScreen.setTextFill(Color.WHITE);
			expScreen.setFont(new Font("Britannic Bold", 20) );
			
			//embedding both the screens as the single major display in a VBox layout
			VBox mainDisplay = new VBox(10);
			mainDisplay.setAlignment(Pos.CENTER_RIGHT);
			mainDisplay.getChildren().addAll(expScreen, screen);
			
			//embedding main display with screen bg to create final main screen
			StackPane mainScreen = new StackPane();
			mainScreen.getChildren().addAll(rect, mainDisplay);
			mainScreen.setAlignment(screen, Pos.BOTTOM_RIGHT);
			
			return mainScreen;
		}
		
		private void rearrangeGrid(GridPane grid) {
			grid.setConstraints(buttons.get("decimal"), 3, 5);
			grid.setConstraints(buttons.get("negative"), 4, 5);
			grid.setConstraints(buttons.get("digit_zero"), 5, 5);
			grid.setConstraints(buttons.get("plus"), 6, 5);
			grid.setConstraints(buttons.get("equals"), 2, 5);

		}
		
		private Scene buildSceneOne() {		
			StackPane mainDisplay = buildBasicLayout(400);
			
			GridPane layout1 = new GridPane();
			layout1.addRow(0, mainDisplay);
			layout1.setColumnSpan(mainDisplay, 2);
			layout1.addRow(1, buttons.get("clear"), buttons.get("backspace"), buttons.get("modulus"), buttons.get("sci"));
			layout1.addRow(2, buttons.get("digit_seven"), buttons.get("digit_eight"), buttons.get("digit_nine"), buttons.get("divide"));
			layout1.addRow(3, buttons.get("digit_four"), buttons.get("digit_five"), buttons.get("digit_six"), buttons.get("multiply"));
			layout1.addRow(4, buttons.get("digit_one"), buttons.get("digit_two"), buttons.get("digit_three"), buttons.get("minus"));
			layout1.addRow(5, buttons.get("decimal"), buttons.get("negative"), buttons.get("digit_zero"), buttons.get("plus"), buttons.get("equals"));
			setGridAlignment(layout1);
			return new Scene(layout1, 400, 450);
		}
		
		private Scene buildSceneTwo() {
			StackPane mainDisplay = buildBasicLayout(692);
			
			GridPane layout2 = new GridPane();
			layout2.addRow(0, mainDisplay);
			layout2.setColumnSpan(mainDisplay, 7);
			layout2.addRow(1, buttons.get("sqrt"),buttons.get("cbrt"), buttons.get("log"), buttons.get("clear"), buttons.get("backspace"), buttons.get("modulus"), buttons.get("sci"));
			layout2.addRow(2, buttons.get("square"), buttons.get("cube"), buttons.get("power"), buttons.get("digit_seven"), buttons.get("digit_eight"), buttons.get("digit_nine"), buttons.get("divide"));
			layout2.addRow(3, buttons.get("mod"), buttons.get("binary_pow"), buttons.get("factorial"), buttons.get("digit_four"), buttons.get("digit_five"), buttons.get("digit_six"), buttons.get("multiply"));
			layout2.addRow(4, buttons.get("pi"), buttons.get("exp"), buttons.get("exp_pow"), buttons.get("digit_one"), buttons.get("digit_two"), buttons.get("digit_three"), buttons.get("minus"));
			layout2.addRow(5, buttons.get("decimal"), buttons.get("negative"), buttons.get("digit_zero"), buttons.get("plus"), buttons.get("equals"));
			setGridAlignment(layout2);
			rearrangeGrid(layout2);
			return new Scene(layout2, 692, 450, Color.BLUE);
		}
		
		private Scene buildSceneThree() {
			StackPane mainDisplay = buildBasicLayout(695);
			
			GridPane layout3 = new GridPane();
			layout3.setAlignment(Pos.CENTER_RIGHT);
			layout3.addRow(0, mainDisplay);
			layout3.setColumnSpan(mainDisplay, 7);
			layout3.addRow(1, buttons.get("sin"),buttons.get("cos"), buttons.get("tan"), buttons.get("clear"), buttons.get("backspace"), buttons.get("modulus"), buttons.get("sci"));
			layout3.addRow(2, buttons.get("sin_inv"), buttons.get("cos_inv"), buttons.get("tan_inv"), buttons.get("digit_seven"), buttons.get("digit_eight"), buttons.get("digit_nine"), buttons.get("divide"));
			layout3.addRow(3, buttons.get("sinh"), buttons.get("cosh"), buttons.get("tanh"), buttons.get("digit_four"), buttons.get("digit_five"), buttons.get("digit_six"), buttons.get("multiply"));
			layout3.addRow(4, buttons.get("digit_one"), buttons.get("digit_two"), buttons.get("digit_three"), buttons.get("minus"));
			layout3.addRow(5, buttons.get("equals"), buttons.get("decimal"), buttons.get("negative"), buttons.get("digit_zero"), buttons.get("plus"));
			setGridAlignment(layout3);
			layout3.setConstraints(buttons.get("digit_one"), 3, 4);
			layout3.setConstraints(buttons.get("digit_two"), 4, 4);
			layout3.setConstraints(buttons.get("digit_three"), 5, 4);
			layout3.setConstraints(buttons.get("minus"), 6,4);
			rearrangeGrid(layout3);
			return new Scene(layout3, 695, 450, Color.BLUE);
		}
		
		//helper method to update the expression
		private void resetExpression() {
			if( state == 0 ) {
				if( operation.contains("x") ) {
					expression = operation.replace("x",input);
				}
				else if( !"+-*/%x^y".contains(operation) ) {
					expression = operation + " " + input;
				}
				else {
				expression = input + " " + operation + " ";
				}
			}
			else {
				if( result%1 == 0 ) {
					expression = Double.toString(inputA).substring(0, Double.toString(inputA).length()-2 ) + " " + operation + " ";
				}
				else {
					expression = Double.toString(inputA) + " " + operation + " ";
				}
			}
			expScreen.setText(expression);
		}
		
		//method for button action for operand keys
		private EventHandler<ActionEvent> addToInput(Button btn) {
			return new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {
					if( state == 2 ) {
						reset();
					}
					if( input.equals("0") ) {
						input = btn.getText();
					}
					else {
						input += btn.getText();
					}
					screen.setText(input);
					System.out.println("Pressed "+btn.getText() +" State : "+state);
				}
			};
		}
		
		//method for button action for operation keys
		private EventHandler<ActionEvent> updateOperation(Button btn) {
			return new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent arg0) {
					System.out.println("update operation");
					if( state == 0 ) {
						inputA = Double.parseDouble(input);
						state = 1;
					}
					else if( state == 2 ) {
						inputA = Double.parseDouble(input);
						state = 1;
					}
					else {
						updateResult();
						inputA = result;
					}
					operation = btn.getText();
					resetExpression();
					input = "0";
				}
			};
		}
		
		private EventHandler<ActionEvent> unaryOperation(Button btn){
			return new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {
					System.out.println("UNARY OPERATION");
					if(state == 0) {
						operation = btn.getText();
						resetExpression();
						inputA = Double.parseDouble(input);
						unaryResult();
						inputA = result;
					}
					else {
						inputB = Double.parseDouble(input);
						String new_operation = operation;
						operation = btn.getText();
						double new_inputA = inputA;
						inputA = inputB;
						unaryResult();
						inputB = result;
						inputA = new_inputA;
						operation = new_operation;
						calculateResult();
						if( inputB%1 == 0) {
							expression = expression + Double.toString(inputB).substring(0, Double.toString(inputB).length()-2);
						}
						else {
							expression = expression + Double.toString(inputB);			
						}
						state = 2;
						expScreen.setText(expression);
						}
					if( result%1 == 0) {
						input =  Integer.toString(((int) result));
					}
					else {
						input = Double.toString(result);
					}					
					screen.setText(input);
					}
			};
		}
		
		//method for button action for decimal key
		private EventHandler<ActionEvent> dotMethod(){
			return new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {
					if( !input.contains(".") ) {
						input += ".";
					}
					screen.setText(input);
				}
			};
		}
		
		//method for button action for negate key
		private EventHandler<ActionEvent> negateMethod(){
			return new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {
				  if( !input.startsWith("0") ) {
					if( input.startsWith("-") ) {
						input = input.substring(1);
					}
					else {
						input = "-" + input;
					}
					screen.setText(input);
				  }
				}
			};
		}
		
		//method for button action for backspace key
		private EventHandler<ActionEvent> backspaceMethod(){
			return new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {
					//keep the input as Integer in case its an integer and change the state appropriately
				   if( state == 2 ) {
					if( result%1 == 0) {
						input =  Integer.toString(((int) result));
					}
					else {
						input = Double.toString(result);
					}
					state = 0;
				  }
				   
				   //implement functionality of removing the rightmost character from input
					if( input.length() != 0 ) {
						if( input.length() != 1 ) {
							input = input.substring(0, input.length()-1);
						}
						else {
							input = "0";
						}
					}
					screen.setText(input);
				}
			};
		}
		
		//method for button action for constant keys
		private EventHandler<ActionEvent> constMethod(Button btn){
			return new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {
					switch(btn.getText()) {
					case "\u03C0" : input = "3.1415926535897932384626433832795";
										break;
					case "e" : input = "2.7182818284590452353602874713527";
					}
					screen.setText(input);
				}
			};
		}
		
		//method for button action for equals key
		private EventHandler<ActionEvent> equalsMethod(){
			return new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {
					if( state != 2 ) {
						expression = expression + input;
						updateResult();
						if( result%1 == 0) {
							input =  Integer.toString(((int) result));
						}
						else {
							input = Double.toString(result);
						}
						state = 2;
						expScreen.setText(expression);
					}
				}
			};
		}
		
		//method for button action for clear_all key
		private EventHandler<ActionEvent> clearMethod(){
			return new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {
					reset();
				}
			};
		}
		
		//method for button action for sci key
		private EventHandler<ActionEvent> sciMethod(){
			return new EventHandler<ActionEvent>() {
				public void handle(ActionEvent arg0) {
					if( mode == 0 ) {
						mainStage.setScene( buildSceneTwo() );
					}
					else if( mode == 1 ) {
						mainStage.setScene( buildSceneThree() );
					}
					else {
						mainStage.setScene( buildSceneOne() );
					}
					mainStage.show();
					mode = (mode+1)%3;
				}
			};
		}
		
		//method to update the result 
		private void updateResult() {
			inputB = Double.parseDouble(input);
			calculateResult();
			if( result %1 == 0 ) {
				screen.setText( Integer.toString( (int) result ) );
			}
			else {
				screen.setText( Double.toString(result) );
			}
		}
		
		//method to calculate the result including all binary operations
		private void calculateResult() {
			System.out.println(inputA + " & "+ inputB);
			switch(operation) {
			case "+" : result = inputA + inputB; break;
			case "-" : result = inputA - inputB; break;
			case "*" : result = inputA * inputB; break;
			case "/" : result = inputA / inputB; break;
			case "%" : result = inputA % inputB; break;
			case "x^y" : result = Math.pow(inputA, inputB); break;
			default : result = Double.MAX_VALUE;
			}
		}
		
		private void unaryResult() {
			System.out.println("UNARY "+ inputA + " & "+ inputB);
			switch(operation) {
			case "e^x" : result = Math.pow(2.7182818284590452353602874713527, inputA); break;
			case "x^2" : result = Math.pow(inputA, 2); break;
			case "x^3" : result = Math.pow(inputA, 3); break;
			case "|x|" : result = Math.abs(inputA); break;
			case "2^x" : result = Math.pow(2, inputA); break;
			case "x!" : result = factorial(inputA); break;
			case "\u221A" : result = Math.pow(inputA, 0.5); break;
			case "\u221B" : result = Math.cbrt(inputA); break;
			case "log" : result = Math.log10(inputA);break;
			case "sin" : result = Math.sin(inputA); break;
			case "cos" : result = Math.cos(inputA); break;
			case "tan" : result = Math.tan(inputA); break;
			case "sin(-1)" : result = Math.asin(inputA); break;
			case "cos(-1)" : result = Math.acos(inputA); break;
			case "tan(-1)" : result = Math.atan(inputA); break;
			case "sin h" : result = Math.sinh(inputA); break;
			case "cos h" : result = Math.cosh(inputA); break;
			case "tan h" : result = Math.tanh(inputA); break;
			default : result = Double.MAX_VALUE;
			}
		}
		
		//helper method to add a button
		private void addButton(String btnName, String btn, int buttonType) {
			Button newButton = new Button(btn);
			newButton.setPrefSize(90, 75);
			newButton.setFont( new Font("Arial", 16) );
			
			EventHandler<ActionEvent> btnAction;
			switch(buttonType) {
			case 0: btnAction = updateOperation(newButton); break;
			case 1: btnAction = unaryOperation(newButton); break;
			case 2: btnAction = addToInput(newButton);break;
			case 3: btnAction = dotMethod(); break;
			case 4: btnAction = negateMethod(); break;
			case 5: btnAction = clearMethod();break;
			case 6: btnAction = backspaceMethod(); break;
			case 7: btnAction = sciMethod();break;
			case 8: btnAction = constMethod(newButton); break;
			case 9: btnAction = equalsMethod();break;
			default :btnAction = new EventHandler<ActionEvent>() { public void handle(ActionEvent arg0) {} };
			}
			
			newButton.setOnAction(btnAction);
			
			buttons.put(btnName, newButton);
		}
		
		//method to initialize all the required buttons
		private void buildButtons() {
			addButton("equals","=", 9);
			addButton("plus", "+", 0);
			addButton("minus", "-", 0);
			addButton("multiply", "*", 0);
			addButton("divide", "/", 0);
			addButton("modulus", "%", 0);
			addButton("power", "x^y", 0);
			addButton("log", "log", 1);
			addButton("exp", "e",8);
			addButton("pi", "\u03C0",8);
			addButton("exp_pow", "e^x",1);
			addButton("square", "x^2", 1);
			addButton("cube", "x^3" ,1);
			addButton("mod", "|x|",1);
			addButton("binary_pow", "2^x",1);
			addButton("factorial", "x!",1);
			addButton("sqrt", "\u221A",1);
			addButton("cbrt", "\u221B",1);
			addButton("sin", "sin",1);
			addButton("cos", "cos",1);
			addButton("tan", "tan",1);
			addButton("sin_inv", "sin(-1)",1);
			addButton("cos_inv", "cos(-1)",1);
			addButton("tan_inv", "tan(-1)",1);
			addButton("sinh", "sin h",1);
			addButton("cosh", "cos h",1);
			addButton("tanh", "tan h",1);
			addButton("sinh_inv", "sin h(-1)",1);
			addButton("cosh_inv", "cos h(-1)",1);
			addButton("tanh_inv", "tan h(-1)",1);
			addButton("digit_one", "1",2);
			addButton("digit_two", "2",2);
			addButton("digit_three", "3",2);
			addButton("digit_four", "4",2);
			addButton("digit_five", "5",2);
			addButton("digit_six", "6",2);
			addButton("digit_seven", "7",2);
			addButton("digit_eight", "8",2);
			addButton("digit_nine", "9",2);
			addButton("digit_zero", "0",2);
			addButton("decimal", ".",3);
			addButton("negative", "+/-",4);
			addButton("clear", "CE",5);
			addButton("backspace", "\u232B",6);
			addButton("sci", "Sci",7);
		}
		
		public void start(Stage primaryStage) throws Exception{
			//starting with initializing all the buttons of main app
			buildButtons();
			//initializing the main layout
						

			primaryStage.setScene( buildSceneOne() );
			primaryStage.setTitle("Calculator");
			primaryStage.setResizable(false);
			primaryStage.show();
			mainStage = primaryStage;
		}
		
		public static void main(String[] args) {
			launch(args);
		}
}
