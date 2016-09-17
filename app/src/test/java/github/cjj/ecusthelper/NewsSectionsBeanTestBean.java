package github.cjj.ecusthelper;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import github.cjj.ecusthelper.bean.NewsSectionsCollection;

/**
 * Created on 2016/9/11
 *
 * @author chenjj2048
 */
public class NewsSectionsBeanTestBean {
    NewsSectionsCollection mSections;

    @Before
    public void init() {
        mSections = new NewsSectionsCollection();
    }

    @Test
    public void testGetInstance() {
        NewsSectionsCollection mInstance = NewsSectionsCollection.getInstance();
        assertNotNull(mInstance);
    }

    @Test
    public void testGetSize() {
        assertEquals(mSections.getSize(), 7);
    }
}
