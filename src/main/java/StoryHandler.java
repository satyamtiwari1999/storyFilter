import org.slf4j.Logger;

import java.util.List;
import java.util.Scanner;

public class StoryHandler {
    public static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(StoryHandler.class);
    private final StoryProcessor storyProcessor = new StoryProcessor();
    private final List<String> storyBits = new java.util.ArrayList<>();
    public void readStory(){
        writeInstructions();
        takeUserInputForStory();
        LOGGER.info("********************* Filtered Story ************************** \n\n");;
        presentStory();
    }

    private void writeInstructions(){
        LOGGER.info("Welcome to the Story Filter Application... \n");
        LOGGER.info("********************* Instructions ************************** \n\n");
        LOGGER.info("To exit the application, please type 'DONE' and press enter");
        LOGGER.info("Please enter the story you would like to filter :\n");
    }

    private void takeUserInputForStory(){
        final Scanner scanner = new Scanner(System.in);
        while(true){
            // Read the story from the user
            final String story = scanner.nextLine();
            if(story.equalsIgnoreCase("done") || story.equalsIgnoreCase("exit")){
                LOGGER.info("Exiting the application...");
                break;
            }
            storyBits.add(storyProcessor.processStory(story));
        }
        scanner.close();
    }

    private void presentStory(){
        LOGGER.info(String.join(" \n", storyBits));
    }
}
