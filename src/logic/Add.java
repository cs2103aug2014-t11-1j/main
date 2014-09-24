package logic;

/**
 *
 * @author Jireh
 */
public class Add extends CommandFactory {
    
    /**
     * Constructor
     */
    protected Add(String input){
        execute(input);
    }
    
    @Override
    protected void execute(String input) {
        list.add(pf.getTask(input));
        updateUndoAndRedoStacks();
    }
    
    @Override
    protected String formatString(String input) {
        return null;
    }
    
}
