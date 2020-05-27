package com.bigzhao.xml2axml.chunks;

import com.bigzhao.xml2axml.IntWriter;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Roy on 15-10-5.
 */
public class TagChunk extends Chunk<Chunk.EmptyHeader> {


    public List<StartNameSpaceChunk> startNameSpace;
    public StartTagChunk startTag;
    public List<TagChunk> content = new LinkedList<TagChunk>();
    public EndTagChunk endTag;
    public List<EndNameSpaceChunk> endNameSpace;

    public TagChunk(Chunk parent, XmlPullParser p) throws XmlPullParserException {
        super(parent);
        if (parent instanceof TagChunk) {
            ((TagChunk) parent).content.add(this);
        } else if (parent instanceof XmlChunk) {
            ((XmlChunk) parent).content = this;
        } else {
            throw new IllegalArgumentException("parent must be XmlChunk or TagChunk");
        }
        startTag = new StartTagChunk(this, p);
        endTag = new EndTagChunk(this, startTag);
        startNameSpace = startTag.startNameSpace;
        endNameSpace = new LinkedList<EndNameSpaceChunk>();
        for (StartNameSpaceChunk c : startNameSpace) endNameSpace.add(new EndNameSpaceChunk(this, c));
        endTag.header.lineNo = startTag.header.lineNo = p.getLineNumber();
    }

    @Override
    public void preWrite() {
        int sum = 0;
        for (StartNameSpaceChunk e : startNameSpace) {
            int res = e.calc();
            sum += res;
        }
        for (EndNameSpaceChunk e : endNameSpace) {
            int res = e.calc();
            sum += res;
        }
        int res = startTag.calc();
        sum += res;
        res = endTag.calc();
        sum += res;
        for (TagChunk c : content) {
            res = c.calc();
            sum += res;
        }
        header.size = sum;
    }

    @Override
    public void writeEx(IntWriter w) throws IOException {
        for (StartNameSpaceChunk c : startNameSpace) {
            c.write(w);
        }
        startTag.write(w);
        for (TagChunk c : content) {
            c.write(w);
        }
        endTag.write(w);
        for (EndNameSpaceChunk c : endNameSpace) {
            c.write(w);
        }
    }


}
