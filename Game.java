import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is the driver class, contains main()
 * This class also instantiates all crop/seed and tool objects
 */
public class Game
{
    private static int days = 1;

    /**
     * Purpose: This method increments the farming day
     * @param tile Object for Tile class, which concerns the crops' daily growth and needs
     */
    public static void advanceDays(Tile tile)
    {
        days++;
        tile.growCrop();
    }

    /**
     * Purpose: This method displays the Game's Main Menu
     */
    public static void displayGameMenu()
    {
        System.out.println(
                """
                                               _.-^-._    .--.
                                            .-'   _   '-. |__|
                            MyFarm         /     |_|     \\|  |
                                          /               \\  |
                                         /|     _____     |\\ |
                                          |    |==|==|    |  |
                      |---|---|---|---|---|    |--|--|    |  |
                      |---|---|---|---|---|    |==|==|    |  |
                     ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^""");

        System.out.println("             Press [1] to START");
        System.out.println("             Press [0] to EXIT");
        System.out.println("          Press [2] to SEE MECHANICS");
        System.out.println("       Press [3] to see ABOUT THE GAME");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

    }

    /**
     * Purpose: This method displays the Game's Mechanics and Objectives
     */
    public static void displayMechanics()
    {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("""
                               Game Mechanics
                 You are a farmer. The goal of the game is to
                  buy seeds, prepare your land, plant seeds,
                 advance days (watch crops grow), and harvest
                  crops. Over time, you can gain experiences,
                  earn more Objectcoins, and level up to
                improve your rank. Make use of tools to help
                 you in your farming quest. The game only
                ceases to run if you have no active/growing
                crops for the day and if you have no enough
                  money left to buy new seeds. Good luck!
                """);
    }

    /**
     * Purpose: This method displays the Game's Information/Details
     */
    public static void displayAboutGame()
    {
        System.out.println("\n        MyFarm: An Object-Based Game\n");
        System.out.println("                Created by:");
        System.out.println("      Angelo Guerra & Vinnie Inocencio\n");
        System.out.println("     This game is in partial fulfillment");
        System.out.println("       of the requirements for CCPROG3\n");
        System.out.println("       Instructor: Mr. Marco Valmores");
        System.out.println("       De La Salle University - Manila");
        System.out.println("              A.Y. 2022 - 2023\n");
    }

    public static void main(String[] args) throws FileNotFoundException {
        boolean gameRun;
        boolean dayEnd;

        int GameMenu;

        Scanner scan = new Scanner(System.in);
        String farmerName;
        int userInput;
        int userInput2;
        int nCounter = 1;
        int tens;
        int ones;
        int num;
        String[] rocks = new String[50];
        int rockIndex = 0;

        // Crop/Seed Instantiation
        Crop Turnip    = new Crop("Turnip", PlantType.ROOT_CROP, 5, 2, 1, 2, 0, 1, 1, 2, 6, 5);
        Crop Carrot    = new Crop("Carrot", PlantType.ROOT_CROP, 10, 3, 1, 2, 0, 1, 1, 2, 9, 7.5);
        Crop Potato    = new Crop("Potato", PlantType.ROOT_CROP, 20, 5, 3, 4,1, 2, 1, 10, 3, 12.5);
        Crop Rose      = new Crop("Rose", PlantType.FLOWER, 5, 1, 1, 2, 0, 1, 1, 1, 5, 2.5);
        Crop Tulips    = new Crop("Tulips", PlantType.FLOWER, 10, 2, 2, 3,0, 1, 1, 1,9, 5);
        Crop Sunflower  = new Crop("Sunflower", PlantType.FLOWER, 20, 3, 2, 3,1, 2,1, 1,19, 7.5);
        Crop Mango     = new Crop("Mango", PlantType.FRUIT_TREE, 100, 10, 7, 7,4, 4, 5, 15, 8, 25);
        Crop Apple     = new Crop("Apple", PlantType.FRUIT_TREE, 200, 10, 7, 7, 5, 5, 10, 15, 5, 25);

        Seed TurnipSeed    = new Seed(Turnip);
        Seed CarrotSeed    = new Seed(Carrot);
        Seed PotatoSeed    = new Seed(Potato);
        Seed RoseSeed      = new Seed(Rose);
        Seed TulipsSeed    = new Seed(Tulips);
        Seed SunflowerSeed  = new Seed(Sunflower);
        Seed MangoSeed     = new Seed(Mango);
        Seed AppleSeed     = new Seed(Apple);

        // Tool Instantiation
        Tool PlowTool    = new Tool("Plow Tool", 0, 0.5);
        Tool WateringCan = new Tool("Watering Can", 0, 0.5);
        Tool Fertilizer  = new Tool("Fertilizer", 10, 4);
        Tool Pickaxe     = new Tool("Pickaxe", 50, 15);
        Tool Shovel      = new Tool("Shovel", 7, 2);

        // Player Details
        System.out.print("Please enter your name: ");
        farmerName = scan.nextLine();
        Farmer farmer = new Farmer(farmerName);

        Farm farm = new Farm();
        File file = new File("C:\\Users\\vinni\\Desktop\\MCO2Rocks.txt");
        Scanner scan3 = new Scanner(file);

        while (scan3.hasNextLine())
        {
            rocks[rockIndex] = scan3.nextLine();
            rockIndex++;
        }
        farm.setRocks(rocks);

        //setting tile numbers
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                farm.getTiles()[i][j].setTileNum(nCounter);
                nCounter++;
            }
        }

        do {
            displayGameMenu();
            GameMenu = scan.nextInt();

            if(GameMenu < 0 || GameMenu > 3)
            {
                do{
                    System.out.println("Invalid input, Enter again.");
                    GameMenu = scan.nextInt();
                } while (GameMenu < 0 || GameMenu > 3);
            }
            if (GameMenu == 2) // Display Game Mechanics
            {
                displayMechanics();
                displayGameMenu();
                GameMenu = scan.nextInt();
            }
            if (GameMenu == 3) // Display About the Game
            {
                displayAboutGame();
                displayGameMenu();
                GameMenu = scan.nextInt();
            }
            if (GameMenu == 1) // Game Proper
            {
                // Main Game Loop
                do {
                    // Display Daily Stats
                    System.out.println("\n" + farmer.getName() + "'s" + " Farm: Day " + days);
                    System.out.println("Level " + farmer.getLevel() + "(" + farmer.getType() + ")");
                    System.out.println("Progress: " + farmer.getExperience() + " exp points");

                    System.out.println("Select a tile: [1 - 50]");
                    userInput2 = scan.nextInt();
                    scan.nextLine();
                    if(userInput2 < 1 || userInput2 > 50)
                    {
                        do{
                            System.out.println("Invalid input, Enter again.");
                            userInput2 = scan.nextInt();
                        }while (userInput2 < 1 || userInput2 > 50);
                    }
                    num = userInput2-1;
                    ones = num%10;
                    tens = num/10;

                    dayEnd = false;
                    do {
                        // Display tile status
                        farm.getTiles().displayTileStatus();

                        // Main Game Menu
                        System.out.println("\n" + farmer.getObjectCoin() + " coins\n");
                        System.out.println("What would you like to do? ");
                        System.out.println("[1] Display Tile Status");
                        System.out.println("[2] Select new tile"); //to implement
                        System.out.println("[3] Buy Seeds");
                        System.out.println("[4] Use Tools");
                        System.out.println("[5] Plant Crops");
                        System.out.println("[6] Harvest Crops");
                        System.out.println("[7] Register for a higher farmer status");
                        System.out.println("[8] End the Day");

                        userInput = scan.nextInt();
                        scan.nextLine();

                        if(userInput < 1 || userInput > 8)
                        {
                            do{
                                System.out.println("Invalid input, Enter again.");
                                userInput = scan.nextInt();
                            } while (userInput < 1 || userInput > 8);
                        }
                        switch (userInput)
                        {
                            case(1):
                                farm.getTiles()[tens][ones].displayTileStatus();
                                break;
                            case (2):
                                System.out.println("Select a tile: [1 - 50]");
                                userInput2 = scan.nextInt();
                                scan.nextLine();
                                if(userInput2 < 1 || userInput2 > 50)
                                {
                                    do{
                                        System.out.println("Invalid input, Enter again.");
                                        userInput2 = scan.nextInt();
                                    }while (userInput2 < 1 || userInput2 > 50);
                                }
                                num = userInput2-1;
                                ones = num%10;
                                tens = num/10;
                                break;
                            case (3): // Buy Seeds
                                System.out.println("Which type of seeds would you like to buy? ");
                                System.out.println("[1] Turnip\n[2] Carrot\n[3] Potato\n[4] Rose\n[5] Tulips\n[6] Sunflower\n[7] Mango\n[8] Apple");
                                userInput2 = scan.nextInt();
                                scan.nextLine();
                                if(userInput2 < 1 || userInput2 > 8)
                                {
                                    System.out.println("Invalid input, Enter again.");
                                    userInput2 = scan.nextInt();
                                }
                                switch (userInput2)
                                {
                                    case (1) -> farmer.buySeeds(TurnipSeed);
                                    case (2) -> farmer.buySeeds(CarrotSeed);
                                    case (3) -> farmer.buySeeds(PotatoSeed);
                                    case (4) -> farmer.buySeeds(RoseSeed);
                                    case (5) -> farmer.buySeeds(TulipsSeed);
                                    case (6) -> farmer.buySeeds(SunflowerSeed);
                                    case (7) -> farmer.buySeeds(MangoSeed);
                                    case (8) -> farmer.buySeeds(AppleSeed);
                                }
                                break;
                            case (4): // Use Tools
                                System.out.println("Select which tool you would like to use: ");
                                System.out.println("[1] Plow Tool\n[2] Watering Can\n[3] Fertilizer\n[4] Pickaxe\n[5] Shovel");
                                userInput2 = scan.nextInt();
                                scan.nextLine();

                                if(userInput2 < 1 || userInput2 > 5)
                                {
                                    System.out.println("Invalid input, Enter again.");
                                    userInput2 = scan.nextInt();
                                }
                                switch (userInput2)
                                {
                                    case (1): // Plow Tool
                                        if (farm.getTiles()[tens][ones].getIsPlowed()) {
                                            System.out.println("This tile is already plowed.");
                                        } else {
                                            System.out.println("This tile has been plowed.");
                                            farmer.useTool(PlowTool, farm.getTiles()[tens][ones]);
                                        }
                                        break;
                                    case (2): // Watering Can
                                        if (!farm.getTiles()[tens][ones].getIsFarmed())
                                        {
                                            System.out.println("There is no crop on this tile.");
                                        } else if (farm.getTiles()[tens][ones].getTimesWateredToday() >= farm.getTiles()[0][0].getWaterBonusLimit()) {
                                            System.out.println("You have already watered this tile the maximum number of times");
                                        } else {
                                            System.out.println("This tile has been watered.");
                                            farmer.useTool(WateringCan, farm.getTiles()[tens][ones]);
                                        }
                                        break;
                                    case (3): // Fertilizer
                                        if (!farm.getTiles()[tens][ones].getIsFarmed()) {
                                            System.out.println("There is no crop on this tile.");
                                        } else if (farm.getTiles()[tens][ones].getTimesFertilizedToday() >= farm.getTiles()[0][0].getFertilizerBonusLimit()) {
                                            System.out.println("You have already fertilized this tile the maximum number of times");
                                        } else {
                                            System.out.println("This tile has been fertilized.");
                                            farmer.useTool(Fertilizer, farm.getTiles()[tens][ones]);
                                        }
                                        break;
                                    case (4): // Pickaxe
                                        if (!farm.getTiles()[tens][ones].getIsRockPlaced()) {
                                            System.out.println("There is no rock on this tile");
                                        } else {
                                            System.out.println("The rock on this tile has been removed.");
                                            farmer.useTool(Pickaxe, farm.getTiles()[tens][ones]);
                                        }
                                        break;
                                    case (5): // Shovel
                                        if (farm.getTiles()[tens][ones].getIsFarmed() && farm.getTiles()[tens][ones].getIsWithered()) {
                                            System.out.println("The withered crop on this tile has been removed.");
                                            farmer.useTool(Shovel, farm.getTiles()[tens][ones]);
                                        } else if (farm.getTiles()[tens][ones].getIsFarmed()) {
                                            System.out.println("The growing crop on this tile has been removed.");
                                            farmer.useTool(Shovel, farm.getTiles()[tens][ones]);
                                        } else {
                                            System.out.println("There is nothing on this tile to shovel.");
                                        }
                                        break;
                                    default:
                                        System.out.println("Invalid input.");
                                        break;
                                }
                                break;
                            case (5): // Plant Crops
                                if (farm.getTiles()[tens][ones].getIsFarmed() && farm.getTiles()[0][0].getIsWithered()) {
                                    System.out.println("There is currently a withered crop on this tile.");
                                } else if (farm.getTiles()[tens][ones].getIsFarmed()) {
                                    System.out.println("There is already a crop growing on this tile.");
                                } else if (farm.getTiles()[tens][ones].getIsRockPlaced()) {
                                    System.out.println("There is currently a rock on this tile.");
                                } else if (!farm.getTiles()[tens][ones].getIsPlowed()) {
                                    System.out.println("You cannot plant on unplowed tiles.");
                                } else {
                                    System.out.println("Select the seed you would like to plant on this tile");
                                    System.out.println("[1] Turnip (" + TurnipSeed.getAmount() + ")\n[2] Carrot  (" + CarrotSeed.getAmount() + ")\n[3] Potato (" + PotatoSeed.getAmount() + ")\n[4] Rose (" + RoseSeed.getAmount() + ")\n[5] Tulips (" + TulipsSeed.getAmount() + ")\n[6] Sunflower (" + SunflowerSeed.getAmount() + ")\n[7] Mango (" + MangoSeed.getAmount() + ")\n[8] Apple (" + AppleSeed.getAmount() + ")");
                                    userInput2 = scan.nextInt();
                                    scan.nextLine();
                                    if(userInput2 < 1 || userInput2 > 8)
                                    {
                                        System.out.println("Invalid input, Enter again.");
                                        userInput2 = scan.nextInt();
                                    }
                                    switch (userInput2)
                                    {
                                        case (1) -> farmer.plantCrop(TurnipSeed, farm.getTiles()[tens][ones]);
                                        case (2) -> farmer.plantCrop(CarrotSeed, farm.getTiles()[tens][ones]);
                                        case (3) -> farmer.plantCrop(PotatoSeed, farm.getTiles()[tens][ones]);
                                        case (4) -> farmer.plantCrop(RoseSeed, farm.getTiles()[tens][ones]);
                                        case (5) -> farmer.plantCrop(TulipsSeed, farm.getTiles()[tens][ones]);
                                        case (6) -> farmer.plantCrop(SunflowerSeed, farm.getTiles()[tens][ones]);
                                        case (7) -> farmer.plantCrop(MangoSeed, tens, ones, farm.getTiles());
                                        case (8) -> farmer.plantCrop(AppleSeed, tens, ones, farm.getTiles());
                                        default -> System.out.println("Invalid Input.");
                                    }
                                }
                                break;
                            case (6): // Harvest Crops
                                if (!farm.getTiles()[tens][ones].getIsFarmed()) {
                                    System.out.println("There is nothing on this tile to harvest.");
                                } else if (farm.getTiles()[tens][ones].getIsWithered()) {
                                    System.out.println("This crop is already withered and cannot be harvested");
                                } else if (!farm.getTiles()[tens][ones].getIsRipe()) {
                                    System.out.println("This crop is not yet ready to be harvested");
                                } else {
                                    System.out.println("The crop on this tile has been harvested");
                                    farmer.harvestCrop(farm.getTiles()[tens][ones]);
                                }
                                break;
                            case (7): // Register for Higher Farmer Status
                                farmer.registerForHigherStatus(farm.getTiles());
                                break;
                            case (8): // End Day
                                advanceDays(farm.getTiles()[tens][ones]);
                                dayEnd = true;
                                break;
                            default:
                                System.out.println("Invalid option, try again.");
                                break;
                        }
                        farmer.levelUp();
                    } while (!dayEnd);
                    if (farm.getTiles()[tens][ones].getIsWithered()) {
                        gameRun = false;
                        System.out.println("Crop wilted. Game over\n");
                        displayGameMenu();
                        GameMenu = scan.nextInt();
                    } else if (farmer.getObjectCoin() < 0) {
                        gameRun = false;
                        System.out.println("Out of money. Game over\n");
                        displayGameMenu();
                        GameMenu = scan.nextInt();
                    } else if (!farm.getTiles()[tens][ones].getIsFarmed()) {
                        gameRun = false;
                        System.out.println("No crops growing. Game over\n");
                    } else {
                        gameRun = true;
                    }
                } while (gameRun);
            }
        } while (GameMenu != 0);
    }
}