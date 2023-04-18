
       import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

        public class Main extends JFrame{
            JPanel mainPanel;

            JPanel optionPanel;
            JRadioButton thin;
            JRadioButton regular;
            JRadioButton deepDish;
            JComboBox sizeSelect;

            JPanel toppingSelectPanel;
            JCheckBox toenails;
            JCheckBox tears;
            JCheckBox snot;
            JCheckBox boogers;
            JCheckBox fingers;
            JCheckBox fish;
            JCheckBox garbage;
            JCheckBox teeth;

            JPanel orderDisplayPanel;
            JTextArea orderTextArea;

            JPanel controlPanel;
            JButton orderButton;
            JButton clearButton;
            JButton quitButton;

            boolean orderPlaced = false;

            public void clearOrder()
            {
                teeth.setSelected(false);
                fingers.setSelected(false);
                fish.setSelected(false);
                boogers.setSelected(false);
                snot.setSelected(false);
                tears.setSelected(false);
                garbage.setSelected(false);
                toenails.setSelected(false);
                regular.setSelected(true);
                sizeSelect.setSelectedIndex(0);

                orderTextArea.setText("");

                subTotal = 0;
                orderPlaced = false;
            }
            public Main()
            {
                mainPanel = new JPanel();
                mainPanel.setLayout(new BorderLayout());

                createOptionPanel();
                mainPanel.add(optionPanel, BorderLayout.NORTH);

                createOrderDisplayPanel();
                mainPanel.add(orderDisplayPanel, BorderLayout.CENTER);

                createControlPanel();
                mainPanel.add(controlPanel, BorderLayout.SOUTH);

                add(mainPanel);
                setSize(600,400);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);
            }

            private void createOptionPanel()
            {
                optionPanel = new JPanel();
                optionPanel.setLayout(new GridLayout(3,4));

                Border titledBorder = BorderFactory.createTitledBorder("Options");
                optionPanel.setBorder(titledBorder);

                thin = new JRadioButton("Thin");
                regular = new JRadioButton("Regular");
                deepDish = new JRadioButton("Deep-Dish");

                optionPanel.add(thin);
                optionPanel.add(regular);
                optionPanel.add(deepDish);

                regular.setSelected(true);

                ButtonGroup crustChoicesGroup = new ButtonGroup();
                crustChoicesGroup.add(thin);
                crustChoicesGroup.add(regular);
                crustChoicesGroup.add(deepDish);

                sizeSelect = new JComboBox();
                sizeSelect.addItem("Small    ($8.00)");
                sizeSelect.addItem("Medium   ($12.00)");
                sizeSelect.addItem("Large    ($16.00)");
                sizeSelect.addItem("Super    ($20.00)");

                optionPanel.add(sizeSelect);

                toenails = new JCheckBox("toe nails");
                boogers = new JCheckBox("boogers");
                tears = new JCheckBox("tears");
                fish = new JCheckBox("fish");
                fingers = new JCheckBox("fingers");
                garbage = new JCheckBox("garbage");
                teeth = new JCheckBox(" teeth");
                snot = new JCheckBox("snot");

                optionPanel.add(toenails);
                optionPanel.add(boogers);
                optionPanel.add(tears);
                optionPanel.add(teeth);
                optionPanel.add(fingers);
                optionPanel.add(snot);
                optionPanel.add(fish);
                optionPanel.add(garbage);
            }



            private void createControlPanel()
            {
                Border titledBorder = BorderFactory.createTitledBorder("Options");
                controlPanel = new JPanel();
                controlPanel.setLayout(new GridLayout(1,4));




                orderButton = new JButton("Order");
                orderButton.addActionListener((ActionEvent ae) -> placeOrder());
                clearButton = new JButton("Clear");
                clearButton.addActionListener((ActionEvent ae) -> clearOrder());
                quitButton = new JButton("Quit");
                quitButton.addActionListener((ActionEvent ae) -> System.exit(0));
                controlPanel.add(orderButton);
                controlPanel.add(clearButton);
                controlPanel.add(quitButton);
                controlPanel.setBorder(titledBorder);
            }

            private void createOrderDisplayPanel()
            {
                orderDisplayPanel = new JPanel();
                orderTextArea = new JTextArea(9, 30);
                Border titledBorder = BorderFactory.createTitledBorder("Order");
                orderDisplayPanel.setBorder(titledBorder);
                orderDisplayPanel.add(orderTextArea);
            }

            private double subTotal;
            public void placeOrder()
            {
                if(!orderPlaced)
                {
                    addSeparators();
                    checkCrustAndSize();
                    checkToppings();
                    calculatePrice();
                    addSeparators();
                    orderPlaced = true;
                }
            }



            public void addSeparators()
            {
                orderTextArea.append("================================================================================ \n");
            }

            public void calculatePrice()
            {
                double tax = subTotal * .07;
                double totalPrice = subTotal + tax;

                orderTextArea.append("Sub Total:      $" + String.format("%.2f",subTotal) + "\n");
                orderTextArea.append("Tax:                 $" + String.format("%.2f",tax) +"\n");
                orderTextArea.append("-------------------------------------------------------------------------------------------------------------------------------------------- \n");
                orderTextArea.append("Total Price:    $" + String.format("%.2f",totalPrice) + "\n");
            }

            public void checkToppings()
            {
                ArrayList<JCheckBox> toppingButtons= new ArrayList<JCheckBox>(Arrays.asList(teeth,toenails,boogers, garbage, tears, fingers, snot, fish));
                ArrayList<String> selectedToppings = new ArrayList<>();
                for(int i = 0; i < 8; i++)
                {
                    if(toppingButtons.get(i).isSelected())
                    {
                        selectedToppings.add(toppingButtons.get(i).getText());
                    }

                }
                orderTextArea.append("Toppings:  ");
                for(int l = 0 ; l < selectedToppings.size() ; l++)
                {
                    subTotal += 1.0;
                    if(l < selectedToppings.size() - 1)
                        orderTextArea.append(selectedToppings.get(l) + ", ");
                    else
                        orderTextArea.append(selectedToppings.get(l));
                }
                orderTextArea.append("    $" + selectedToppings.size() + ".00 \n");

            }



            public void checkCrustAndSize()
            {
                String chosenCrust = "";
                if(thin.isSelected())
                {
                    chosenCrust = "Thin";
                }
                if(regular.isSelected())
                {
                    chosenCrust = "Regular";
                }
                if(deepDish.isSelected())
                {
                    chosenCrust = "Deep-Dish";
                }

                String chosenSize = sizeSelect.getSelectedItem().toString();
                if(chosenSize == "Small    ($8.00)"){
                    orderTextArea.append("Size, Style:   Small, "+ chosenCrust + "     $8.00 \n");
                    subTotal += 8.0;
                }
                if(chosenSize == "Medium   ($12.00)")
                {
                    orderTextArea.append("Size, Style:   Medium, "+ chosenCrust + "     $12.00 \n");
                    subTotal += 12.0;
                }
                if(chosenSize == "Large    ($16.00)")
                {
                    orderTextArea.append("Size, Style:   Large, " + chosenCrust + "     $16.00 \n");
                    subTotal += 16.0;
                }
                if(chosenSize == "Super    ($20.00)")
                {
                    orderTextArea.append("Size, Style:   Super, " + chosenCrust + "     $20.00 \n");
                    subTotal += 20.0;
                }
            }
        }

