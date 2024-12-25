package ca.dal.csci5308.assignment1;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.mockito.Mock;

import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TrackerTest {
    @Mock
    private DatabaseService db;

    @InjectMocks
    private Tracker tracker;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tracker = new Tracker(db);
    }

    @Test
    void validatePathTest(){
        assertFalse(tracker.validatePath(null));
    }

    @Test
    void connectDBTest() throws InterruptedException {
        when(db.connect()).thenReturn(true);
        assertTrue(tracker.connectDB());
        verify(db).connect();
    }

    @Test
    void disconnectDBTest(){
        when(db.disconnect()).thenReturn(true);
        assertTrue(tracker.disconnectDB());
        verify(db).disconnect();
    }

    @Test
    void addLogTest() {
        String path = "/valid/path";
        Date timeStamp = new Date();
        tracker.addLog(path, timeStamp);
        verify(db).addActivityLog(path, timeStamp);
    }

    @Test
    void getLogTest() {
        Map<String, Date> logs = Map.of("/path1", new Date(), "/path2", new Date());
        when(db.getActivityLogs()).thenReturn(logs);
        assertEquals(logs, tracker.getLog());
    }

}