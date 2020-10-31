package refactoring;

import javafx.geometry.Pos;

import java.awt.font.FontRenderContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

public class Rover {
	private Heading heading;
	private Position position;

	public Rover(String facing, int x, int y) {
		position = new Position(x,y);
		heading = Heading.of(facing);
	}

	public Rover(Heading heading, int x, int y) {
		position = new Position(x,y);
		this.heading = heading;
	}

	public Rover(Heading heading, Position position) {
		this.position = position;
		this.heading = heading;
	}

	public Heading heading() {
		return heading;
	}

	public Position position() {
		return position;
	}

	public void go(Order... orders) {
		for (Order order:orders) execute(order);
	}

	public void go(String instructions) {
		String[] orders = instructions.split("");
		Stream<Order> stream = stream(orders).map(Order::of).filter(Objects::nonNull);
		stream.forEach(this::execute);
	}

	private void execute(Order order) {
		actions.get(order).execute();
	}

	private Map<Order, Action> actions = new HashMap<>();
	{
		actions.put(Order.Left, () -> {heading = heading.turnLeft();});
		actions.put(Order.Right, () -> {heading = heading.turnRight();});
		actions.put(Order.Forward, () -> {position = position.forward(heading);});
		actions.put(Order.Backward, () -> {position = position.backward(heading);});
	}

	@FunctionalInterface
	public interface Action {
		void execute();
	}

	public static class Position {
		private final int x;
		private final int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Position forward(Heading heading) {
			if(heading == Heading.North) return new Position(x,y+1);
			if(heading == Heading.South) return new Position(x,y-1);
			if(heading == Heading.East) return new Position(x+1,y);
			if(heading == Heading.West) return new Position(x-1,y);
			return null;
		}

		public Position backward(Heading heading) {
			if(heading == Heading.North) return new Position(x,y-1);
			if(heading == Heading.South) return new Position(x,y+1);
			if(heading == Heading.East) return new Position(x-1,y);
			if(heading == Heading.West) return new Position(x+1,y);
			return null;
		}

		@Override
		public boolean equals(Object object) {
			return isSameClass(object) && equals((Position) object);
		}

		private boolean equals(Position position) {
			return position == this || (x == position.x && y == position.y);
		}

		private boolean isSameClass(Object object) {
			return object != null && object.getClass() == Position.class;
		}

	}

	public enum Heading {
		North, East, South, West;

		public static Heading of(String label) {
			return of(label.charAt(0));
		}

		public static Heading of(char label) {
			if (label == 'N') return North;
			if (label == 'S') return South;
			if (label == 'W') return West;
			if (label == 'E') return East;
			return null;
		}

		public Heading turnRight() {
			return values()[add(+1)];
		}

		public Heading turnLeft() {
			return values()[add(-1)];
		}

		private int add(int offset) {
			return (this.ordinal() + offset + values().length) % values().length;
		}

	}

	public enum Order {
		Forward, Backward, Right, Left;

		public static Order of(String label) { return of(label.charAt(0));}

		public static Order of(char label) {
			if(label == 'R') return Right;
			if(label == 'L') return Left;
			if(label == 'F') return	Forward;
			if(label == 'B') return Backward;
			return null;
		}
	}


}

