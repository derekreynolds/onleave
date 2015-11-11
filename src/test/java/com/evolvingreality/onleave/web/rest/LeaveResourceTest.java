package com.evolvingreality.onleave.web.rest;

import com.evolvingreality.onleave.model.Leave;
import com.evolvingreality.onleave.model.User;
import com.evolvingreality.onleave.service.LeaveService;
import com.evolvingreality.onleave.service.LeaveTestUtil;
import com.evolvingreality.onleave.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Test class for the LeaveResource REST controller.
 *
 * @see LeaveResource
 */
public class LeaveResourceTest {

    @Mock
    private LeaveService leaveService;
    
    @Mock
    private UserService userService;
    
    private MockMvc restMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        LeaveResource publicHolidayResource = new LeaveResource(leaveService, userService);

        this.restMvc = MockMvcBuilders.standaloneSetup(publicHolidayResource)
        		.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();

    }

    @Test
    public void testGetAll() throws Exception {
    	
    	Leave leave1 = LeaveTestUtil.create();
    	Leave leave2 = LeaveTestUtil.create();
    	
    	List<Leave> leaves = new ArrayList<>();
    	leaves.add(leave1);
    	leaves.add(leave2);
    	
    	Page<Leave> page = new PageImpl<>(leaves);
    	
    	doReturn(page).when(leaveService).getPage(any());
    	
    	restMvc.perform(get("/api/v1.0/user/1/leave")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(2))
                /*.andDo(print())*/;
    }
    
    @Test
    public void testGetOne() throws Exception {
    	
    	Optional<Leave> leave = Optional.of(LeaveTestUtil.create());
    	    	    	
    	doReturn(leave).when(leaveService).get(1L);
    	  	
    	restMvc.perform(get("/api/v1.0/leave/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.leaveType").value("ANNUAL"))
                .andExpect(jsonPath("$.leaveStatus").value("REQUESTED"))
                /*.andDo(print())*/;
    }
    
    @Test
    public void testPost() throws Exception {
    	
    	Leave leave = LeaveTestUtil.create();
   	
    	User u = new User();
    	u.setId(1L);    	
    	
    	Optional<User> user = Optional.of(u);
    	
    	doReturn(user).when(userService).get(1L);
    	
    	doReturn(Boolean.FALSE).when(leaveService).hasAnnualLeaveRequestAlready(any(), any());
    	
    	doAnswer(new Answer<Leave>() {
            @Override
			public Leave answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                if (args[0] instanceof Leave)
                	((Leave)args[0]).setId(1L);
                
                return null;
            }
        }).when(leaveService).save(any(Leave.class));    	
    	
    	restMvc.perform(post("/api/v1.0/user/1/leave")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
    			.content(TestUtil.convertObjectToJsonBytes(leave))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/v1.0/leave/1"))
                /*.andDo(print())*/;
    	
    	verify(leaveService, times(1)).save(any(Leave.class));
    }
    
    @Test
    public void testPostConflict() throws Exception {
    	
    	Leave leave = LeaveTestUtil.create();
    	    
    	User u = new User();
    	u.setId(1L);    	
    	
    	Optional<User> user = Optional.of(u);
    	
    	doReturn(user).when(userService).get(1L);
    	
    	doReturn(Boolean.TRUE).when(leaveService).hasAnnualLeaveRequestAlready(any(), any());
    	
    	doAnswer(new Answer<Leave>() {
            @Override
			public Leave answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                if (args[0] instanceof Leave)
                	((Leave)args[0]).setId(1L);
                
                return null;
            }
        }).when(leaveService).save(any(Leave.class));  
    	
    	restMvc.perform(post("/api/v1.0/user/1/leave")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
    			.content(TestUtil.convertObjectToJsonBytes(leave))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                /*.andDo(print())*/;
    }
    
    @Test
    public void testPut() throws Exception {
    	
    	Leave publicHoliday = LeaveTestUtil.create();    	    
   	
    	restMvc.perform(put("/api/v1.0/leave")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
    			.content(TestUtil.convertObjectToJsonBytes(publicHoliday))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                /*.andDo(print())*/;
    	
    	verify(leaveService, times(1)).save(any(Leave.class));
    }
    
    @Test
    public void testDelete() throws Exception {
    	
     	restMvc.perform(delete("/api/v1.0/leave/1")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                /*.andDo(print())*/;
    	
    	verify(leaveService, times(1)).delete(anyLong());
    }

}

