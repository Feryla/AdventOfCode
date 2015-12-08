package net.feryla.adventofcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author feryla
 */
public class Day7 {

    private static final String input = Helper.loadInput("day7").trim();

    private static final Map<String, Gate> gateMap = new HashMap<>();
    private static final Map<String, Wire> wireMap = new HashMap<>();

    public static void main(String[] args) {
        Arrays.asList(input.split("\n")).stream().forEach(str -> build(str));
        int val = wireMap.get("a").val();
        System.out.println("Part 1: "+ val);
        wireMap.get("b").sourceVal = val;
        wireMap.values().stream().forEach(w -> w.reset());
        val = wireMap.get("a").val();
        System.out.println("Part 2: "+ val);
    }

    private static void build(String str) {
        String[] sides = str.split("->");
        String[] left = sides[0].trim().split(" ");
        String right = sides[1].trim();

        if (left.length == 3) {
            Wire one = getOrCreateWire(left[0]);
            Wire two = getOrCreateWire(left[2]);
            BitOp op = BitOp.valueOf(left[1]);
            Gate g = new Gate(one, two, op);
            Wire r = getOrCreateWire(right);
            r.sourceGate = g;
            wireMap.put(r.id, r);
        } else if (left.length == 2) {
            BitOp op = BitOp.NOT;
            Wire one = getOrCreateWire(left[1]);
            Gate g = new Gate(one, null, op);
            Wire r = getOrCreateWire(right);
            r.sourceGate = g;
            wireMap.put(r.id, r);
        } else if (left.length == 1) {
            Wire r = getOrCreateWire(right);
            try {
                r.sourceVal = Integer.valueOf(left[0].trim());
            } catch (Exception e) {
                Wire w = getOrCreateWire(left[0].trim());
                r.sourcewire = w;
            }
            wireMap.put(r.id, r);
        }
    }

    private static Wire getOrCreateWire(String id) {
        if (wireMap.containsKey(id)) {
            return wireMap.get(id);
        } else {
            Wire w = new Wire(id);
            wireMap.put(id, w);
            return w;
        }
    }
}

class Wire {

    boolean computed = false;
    String id;
    int sourceVal;
    Gate sourceGate;
    Wire sourcewire;

    public Wire(String id) {
        this.id = id;
        try {
            Integer valueOf = Integer.valueOf(id);
            sourceVal = valueOf;
        } catch (Exception e) {
        }

    }

    int val() {
        if (computed) {
            return sourceVal;
        }
        this.computed = true;
        if (sourceGate != null) {
            sourceVal = sourceGate.val();
        } else if (sourcewire != null) {
            sourceVal = sourcewire.val();
        }
        return sourceVal;
    }

    void reset() {
        computed = false;
    }
}

class Gate {

    public Wire one;
    public Wire two;
    public BitOp op;

    public Gate(Wire one, Wire two, BitOp op) {
        this.one = one;
        this.two = two;
        this.op = op;
    }

    int val() {
        if (op == null) {
            return one.val();
        }

        switch (op) {
            case AND:
                return one.val() & two.val();
            case OR:
                return one.val() | two.val();
            case LSHIFT:
                return one.val() << two.val();
            case RSHIFT:
                return one.val() >> two.val();
            case NOT:
                return ~one.val();
            default:
                throw new IllegalStateException("WTF");
        }
    }
}

enum BitOp {

    AND,
    OR,
    LSHIFT,
    RSHIFT,
    NOT
}
