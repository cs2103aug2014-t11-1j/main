package logic;

/**
 *
 * @author Jireh
 */
public class BlockTentative extends CommandFactory {

    private boolean isDone;

    protected BlockTentative(String input) {
        execute(input);
        updateGlobalBlockedTimePeriods();
        //currently no undo/redo for this command
    }

    @Override
    protected void execute(String input) {
        //need parser to implement method to retrieve TentativeTask
        //TentativeTask temp = pf.getTentativeTask(input);

        tentativeTasks.add(temp);
        isDone = true;
    }

    @Override
    protected boolean isDone() {
        return isDone;
    }
}
