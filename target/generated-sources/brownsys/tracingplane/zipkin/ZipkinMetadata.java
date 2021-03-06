/** Generated by BaggageBuffersCompiler */
package brownsys.tracingplane.zipkin;

import edu.brown.cs.systems.tracingplane.baggage_buffers.api.Struct;
import edu.brown.cs.systems.tracingplane.baggage_buffers.impl.StructHelpers;
import brownsys.tracingplane.zipkin.ZipkinFlags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.brown.cs.systems.tracingplane.baggage_buffers.impl.BBUtils;
import java.nio.ByteBuffer;


public class ZipkinMetadata implements Struct {

    private static final Logger _log = LoggerFactory.getLogger(ZipkinMetadata.class);

    public Long traceIdHigh = 0L;
    public Long traceId = 0L;
    public Long spanId = 0L;
    public Long parentId = 0L;
    public ZipkinFlags flags = new ZipkinFlags();

    private static final ZipkinMetadata _defaultValue = new ZipkinMetadata();
    private static final Long _traceIdHigh_defaultValue = 0L;
    private static final Long _traceId_defaultValue = 0L;
    private static final Long _spanId_defaultValue = 0L;
    private static final Long _parentId_defaultValue = 0L;
    private static final ZipkinFlags _flags_defaultValue = new ZipkinFlags();

    @Override
    public Struct.StructHandler<?> handler() {
        return Handler.instance;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("ZipkinMetadata{\n");
            b.append(BBUtils.indent(String.format("traceIdHigh = %s\n", String.valueOf(this.traceIdHigh == null ? _traceIdHigh_defaultValue : this.traceIdHigh))));
            b.append(BBUtils.indent(String.format("traceId = %s\n", String.valueOf(this.traceId == null ? _traceId_defaultValue : this.traceId))));
            b.append(BBUtils.indent(String.format("spanId = %s\n", String.valueOf(this.spanId == null ? _spanId_defaultValue : this.spanId))));
            b.append(BBUtils.indent(String.format("parentId = %s\n", String.valueOf(this.parentId == null ? _parentId_defaultValue : this.parentId))));
            b.append(BBUtils.indent(String.format("flags = %s\n", String.valueOf(this.flags == null ? _flags_defaultValue : this.flags))));
            b.append("}");
        return b.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return ZipkinMetadata.equals(this, _defaultValue);
        } else if (!(other instanceof ZipkinMetadata)) {
            return false;
        } else {
            return ZipkinMetadata.equals(this, (ZipkinMetadata) other);
        }
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result * 37 + (this.traceIdHigh == null ? _traceIdHigh_defaultValue : this.traceIdHigh).hashCode();
        result = result * 37 + (this.traceId == null ? _traceId_defaultValue : this.traceId).hashCode();
        result = result * 37 + (this.spanId == null ? _spanId_defaultValue : this.spanId).hashCode();
        result = result * 37 + (this.parentId == null ? _parentId_defaultValue : this.parentId).hashCode();
        result = result * 37 + (this.flags == null ? _flags_defaultValue : this.flags).hashCode();
        return result;
    }

    private static boolean equals(ZipkinMetadata a, ZipkinMetadata b) {
        if (!BBUtils.equals(a.traceIdHigh, b.traceIdHigh, _traceIdHigh_defaultValue)) return false;
        if (!BBUtils.equals(a.traceId, b.traceId, _traceId_defaultValue)) return false;
        if (!BBUtils.equals(a.spanId, b.spanId, _spanId_defaultValue)) return false;
        if (!BBUtils.equals(a.parentId, b.parentId, _parentId_defaultValue)) return false;
        if (!BBUtils.equals(a.flags, b.flags, _flags_defaultValue)) return false;
        return true;
    }

    public static class Handler implements Struct.StructHandler<ZipkinMetadata> {

        public static final Handler instance = new Handler();

        private Handler(){}


        private static final Struct.StructReader<Long> _traceIdHighReader = StructHelpers.fixed64Reader;
        private static final Struct.StructSizer<Long> _traceIdHighSizer = StructHelpers.fixed64Sizer;
        private static final Struct.StructWriter<Long> _traceIdHighWriter = StructHelpers.fixed64Writer;

        private static final Struct.StructReader<Long> _traceIdReader = StructHelpers.fixed64Reader;
        private static final Struct.StructSizer<Long> _traceIdSizer = StructHelpers.fixed64Sizer;
        private static final Struct.StructWriter<Long> _traceIdWriter = StructHelpers.fixed64Writer;

        private static final Struct.StructReader<Long> _spanIdReader = StructHelpers.fixed64Reader;
        private static final Struct.StructSizer<Long> _spanIdSizer = StructHelpers.fixed64Sizer;
        private static final Struct.StructWriter<Long> _spanIdWriter = StructHelpers.fixed64Writer;

        private static final Struct.StructReader<Long> _parentIdReader = StructHelpers.fixed64Reader;
        private static final Struct.StructSizer<Long> _parentIdSizer = StructHelpers.fixed64Sizer;
        private static final Struct.StructWriter<Long> _parentIdWriter = StructHelpers.fixed64Writer;

        private static final Struct.StructHandler<ZipkinFlags> _flagsHandler = ZipkinFlags.Handler.instance;

        @Override
        public ZipkinMetadata readFrom(ByteBuffer buf) throws Exception {
            ZipkinMetadata instance = new ZipkinMetadata();

            try {
                instance.traceIdHigh = _traceIdHighReader.readFrom(buf);
                instance.traceId = _traceIdReader.readFrom(buf);
                instance.spanId = _spanIdReader.readFrom(buf);
                instance.parentId = _parentIdReader.readFrom(buf);
                instance.flags = _flagsHandler.readFrom(buf);
            } catch (Exception e) {
                _log.warn("Exception parsing ZipkinMetadata ", e);
            }

            return instance;
        }

        @Override
        public void writeTo(ByteBuffer buf, ZipkinMetadata instance) {
            try {
                _traceIdHighWriter.writeTo(buf, instance.traceIdHigh == null ? _traceIdHigh_defaultValue : instance.traceIdHigh);
                _traceIdWriter.writeTo(buf, instance.traceId == null ? _traceId_defaultValue : instance.traceId);
                _spanIdWriter.writeTo(buf, instance.spanId == null ? _spanId_defaultValue : instance.spanId);
                _parentIdWriter.writeTo(buf, instance.parentId == null ? _parentId_defaultValue : instance.parentId);
                _flagsHandler.writeTo(buf, instance.flags == null ? _flags_defaultValue : instance.flags);
            } catch (Exception e) {
                _log.warn("Exception serializing ZipkinMetadata ", e);
            }
        }

        @Override
        public int serializedSize(ZipkinMetadata instance) {
            int size = 0;
            size += _traceIdHighSizer.serializedSize(instance.traceIdHigh == null ? _traceIdHigh_defaultValue : instance.traceIdHigh);
            size += _traceIdSizer.serializedSize(instance.traceId == null ? _traceId_defaultValue : instance.traceId);
            size += _spanIdSizer.serializedSize(instance.spanId == null ? _spanId_defaultValue : instance.spanId);
            size += _parentIdSizer.serializedSize(instance.parentId == null ? _parentId_defaultValue : instance.parentId);
            size += _flagsHandler.serializedSize(instance.flags == null ? _flags_defaultValue : instance.flags);
            return size;
        }
    }
}