/** Generated by BaggageBuffersCompiler */
package edu.brown.cs.systems.tracingplane.opentracing;

import edu.brown.cs.systems.tracingplane.baggage_buffers.BaggageBuffers;
import edu.brown.cs.systems.tracingplane.baggage_buffers.Registrations;
import edu.brown.cs.systems.tracingplane.baggage_buffers.api.Bag;
import edu.brown.cs.systems.tracingplane.baggage_buffers.api.BaggageHandler;
import edu.brown.cs.systems.tracingplane.baggage_buffers.api.Brancher;
import edu.brown.cs.systems.tracingplane.baggage_buffers.api.Joiner;
import edu.brown.cs.systems.tracingplane.baggage_buffers.api.Parser;
import edu.brown.cs.systems.tracingplane.baggage_buffers.api.Serializer;
import edu.brown.cs.systems.tracingplane.baggage_buffers.impl.BBUtils;
import edu.brown.cs.systems.tracingplane.baggage_buffers.impl.Branchers;
import edu.brown.cs.systems.tracingplane.baggage_buffers.impl.Joiners;
import edu.brown.cs.systems.tracingplane.baggage_buffers.impl.Parsers;
import edu.brown.cs.systems.tracingplane.baggage_buffers.impl.Serializers;
import edu.brown.cs.systems.tracingplane.baggage_layer.BagKey;
import edu.brown.cs.systems.tracingplane.baggage_layer.protocol.BaggageReader;
import edu.brown.cs.systems.tracingplane.baggage_layer.protocol.BaggageWriter;
import edu.brown.cs.systems.tracingplane.opentracing.HTraceID;
import edu.brown.cs.systems.tracingplane.transit_layer.Baggage;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HTraceContext implements Bag {

    private static final Logger _log = LoggerFactory.getLogger(HTraceContext.class);

    public Set<HTraceID> parents = null;

    public boolean _overflow = false;

    /**
    * <p>
    * Get the {@link HTraceContext} set in the {@link Baggage} carried by the current thread. If no baggage is being
    * carried by the current thread, or if there is no HTraceContext in it, then this method returns {@code null}.
    * </p>
    *
    * <p>
    * To get HTraceContext from a specific Baggage instance, use {@link #getFrom(Baggage)}.
    * </p>
    *
    * @return the HTraceContext being carried in the {@link Baggage} of the current thread, or {@code null}
    *         if none is being carried. The returned instance maybe be modified and modifications will be reflected in
    *         the baggage.
    */
    public static HTraceContext get() {
        Bag bag = BaggageBuffers.get(Handler.registration());
        if (bag instanceof HTraceContext) {
            return (HTraceContext) bag;
        } else {
            return null;
        }
    }

    /**
    * <p>
    * Get the {@link HTraceContext} set in {@code baggage}. If {@code baggage} has no HTraceContext set then
    * this method returns null.
    * </p>
    *
    * <p>
    * This method does <b>not</b> affect the Baggage being carried by the current thread.  To get HTraceContext
    * from the current thread's Baggage, use {@link #get()}.
    * </p>
    *
    * @param baggage A baggage instance to get the {@link HTraceContext} from
    * @return the {@link HTraceContext} instance being carried in {@code baggage}, or {@code null} if none is being carried.
    *         The returned instance can be modified, and modifications will be reflected in the baggage.
    */
    public static HTraceContext getFrom(Baggage baggage) {
        Bag bag = BaggageBuffers.get(baggage, Handler.registration());
        if (bag instanceof HTraceContext) {
            return (HTraceContext) bag;
        } else if (bag != null) {
            Handler.checkRegistration();
        }
        return null;
    }

    /**
    * <p>
    * Update the {@link HTraceContext} set in the current thread's baggage. This method will overwrite any existing
    * HTraceContext set in the current thread's baggage.
    * </p>
    *
    * <p>
    * To set the {@link HTraceContext} in a specific {@link Baggage} instance, use
    * {@link #setIn(Baggage, HTraceContext)}
    * </p>
    *
    * @param hTraceContext the new {@link HTraceContext} to set in the current thread's {@link Baggage}. If {@code null}
    *            then any existing mappings will be removed.
    */
    public static void set(HTraceContext hTraceContext) {
        BaggageBuffers.set(Handler.registration(), hTraceContext);
    }

    /**
    * <p>
    * Update the {@link HTraceContext} set in {@code baggage}. This method will overwrite any existing
    * HTraceContext set in {@code baggage}.
    * </p>
    *
    * <p>
    * This method does <b>not</b> affect the {@link Baggage} being carried by the current thread. To set the
    * {@link HTraceContext} for the current thread, use {@link #set(HTraceContext)}
    * </p>
    *
    * @param baggage A baggage instance to set the {@link HTraceContext} in
    * @param hTraceContext the new HTraceContext to set in {@code baggage}. If {@code null}, it will remove any
    *            mapping present.
    * @return a possibly new {@link Baggage} instance that contains all previous mappings plus the new mapping.
    */
    public static Baggage setIn(Baggage baggage, HTraceContext hTraceContext) {
        return BaggageBuffers.set(baggage, Handler.registration(), hTraceContext);
    }

    @Override
    public BaggageHandler<?> handler() {
        return Handler.instance;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("HTraceContext{\n");
            b.append(this.parents == null ? "" : BBUtils.indent(String.format("parents = %s\n", BBUtils.toString(this.parents))));
            b.append("}");
        return b.toString();
    }

    public static class Handler implements BaggageHandler<HTraceContext> {

        public static final Handler instance = new Handler();
        private static BagKey registration = null;

        static synchronized BagKey checkRegistration() {
            registration = Registrations.lookup(instance);
            if (registration == null) {
                _log.error("HTraceContext MUST be registered to a key before it can be propagated.  " +
                "There is currently no registration for HTraceContext and it will not be propagated. " +
                "To register a bag set the baggage-buffers.bags property in your application.conf " +
                "or with -Dbaggage-buffers.bags flag (eg, for key 10, -Dbaggage-buffers.bags.10=" + HTraceContext.class.getName());
            }
            return registration;
        }

        static BagKey registration() {
            return registration == null ? checkRegistration() : registration;
        }

        private Handler(){}

        private static final BagKey _parentsKey = BagKey.indexed(0);

        private static final Parser<Set<HTraceID>> _parentsParser = Parsers.setParser(HTraceID.Handler.instance);
        private static final Serializer<Set<HTraceID>> _parentsSerializer = Serializers.setSerializer(HTraceID.Handler.instance);
        private static final Brancher<Set<HTraceID>> _parentsBrancher = Branchers.<HTraceID>set();
        private static final Joiner<Set<HTraceID>> _parentsJoiner = Joiners.<HTraceID>setUnion();

        @Override
        public boolean isInstance(Bag bag) {
            return bag == null || bag instanceof HTraceContext;
        }

        @Override
        public HTraceContext parse(BaggageReader reader) {
            HTraceContext instance = new HTraceContext();

            if (reader.enter(_parentsKey)) {
                instance.parents = _parentsParser.parse(reader);
                reader.exit();
            }
            instance._overflow = reader.didOverflow();

            return instance;
        }

        @Override
        public void serialize(BaggageWriter writer, HTraceContext instance) {
            if (instance == null) {
                return;
            }

            writer.didOverflowHere(instance._overflow);

            if (instance.parents != null) {
                writer.enter(_parentsKey);
                _parentsSerializer.serialize(writer, instance.parents);
                writer.exit();
            }
        }

        @Override
        public HTraceContext branch(HTraceContext instance) {
            if (instance == null) {
                return null;
            }

            HTraceContext newInstance = new HTraceContext();
            newInstance.parents = _parentsBrancher.branch(instance.parents);
            return newInstance;
        }

        @Override
        public HTraceContext join(HTraceContext left, HTraceContext right) {
            if (left == null) {
                return right;
            } else if (right == null) {
                return left;
            } else {
                left.parents = _parentsJoiner.join(left.parents, right.parents);
                return left;
            }
        }
    }
}