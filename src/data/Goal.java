package data;

public class Goal {
    private final goalTypes goal;

    public Goal(goalTypes goal) {
        if (goal == null) throw new NullPointerException("El parámetro es null");
        this.goal = goal;
    }

    public goalTypes getGoal() {
        return goal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return this.goal.equals(goal.goal);
    }

    @Override
    public int hashCode() { return goal.hashCode(); }

    @Override
    public String toString() {
        return "Goal{" + "finalidad petición='" + goal + '\'' + '}';
    }
}
