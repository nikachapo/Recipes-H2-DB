package command;

public class Command {

    private final String name;
    private final Executable executable;

    public Command(String name, Executable executable) {
        this.name = name;
        this.executable = executable;
    }

    public void execute() {
        executable.execute();
    }

    @Override
    public String toString() {
        return name;
    }

    @FunctionalInterface
    public interface Executable {
        void execute();
    }
}
