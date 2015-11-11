package com.evolvingreality.onleave.web.rest;

import com.evolvingreality.onleave.model.HolidayCalendar;
import com.evolvingreality.onleave.service.HolidayCalendarService;
import com.evolvingreality.onleave.service.HolidayCalendarTestUtil;
import com.evolvingreality.onleave.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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
 * Test class for the HolidayCalendarResource REST controller.
 *
 * @see HolidayCalendarService
 */
public class HolidayCalendarResourceTest {

    @Mock
    private HolidayCalendarService holidayCalendarService;

    @Mock
    private UserService userService;
    
    private MockMvc restMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        HolidayCalendarResource holidayCalendarResource = new HolidayCalendarResource(holidayCalendarService);

        this.restMvc = MockMvcBuilders.standaloneSetup(holidayCalendarResource)
        		.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();

    }

    @Test
    public void testGetAll() throws Exception {
    	
    	HolidayCalendar holidayCalendar1 = HolidayCalendarTestUtil.create();
    	HolidayCalendar holidayCalendar2 = HolidayCalendarTestUtil.create();
    	
    	List<HolidayCalendar> holidayCalendars = new ArrayList<>();
    	holidayCalendars.add(holidayCalendar1);
    	holidayCalendars.add(holidayCalendar2);
    	
    	Page<HolidayCalendar> page = new PageImpl<>(holidayCalendars);
    	
    	doReturn(page).when(holidayCalendarService).getPage(any());
    	
    	restMvc.perform(get("/api/v1.0/holidaycalendar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(2))
                /*.andDo(print())*/;
    }
    
    @Test
    public void testGetOne() throws Exception {
    	
    	Optional<HolidayCalendar> holidayCalendar = Optional.of(HolidayCalendarTestUtil.create());
    	    	    	
    	doReturn(holidayCalendar).when(holidayCalendarService).get(1L);
    	  	
    	restMvc.perform(get("/api/v1.0/holidaycalendar/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())                
                .andExpect(jsonPath("$.title").value("Test Holiday Calendar"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                /*.andDo(print())*/;
    }
    
    @Test
    public void testPost() throws Exception {
    	
    	HolidayCalendar holidayCalendar = HolidayCalendarTestUtil.create();
    	
    	doAnswer(new Answer<HolidayCalendar>() {
            public HolidayCalendar answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                if (args[0] instanceof HolidayCalendar)
                	((HolidayCalendar)args[0]).setId(1L);
                
                return null;
            }
        }).when(holidayCalendarService).save(any(HolidayCalendar.class));    	
    	
    	restMvc.perform(post("/api/v1.0/holidaycalendar")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
    			.content(TestUtil.convertObjectToJsonBytes(holidayCalendar))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/v1.0/holidaycalendar/1"))
                /*.andDo(print())*/;
    	
    	verify(holidayCalendarService, times(1)).save(any(HolidayCalendar.class));
    }
        
    @Test
    public void testPut() throws Exception {
    	
    	HolidayCalendar holidayCalendar = HolidayCalendarTestUtil.create();    	    
   	
    	restMvc.perform(put("/api/v1.0/holidaycalendar")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
    			.content(TestUtil.convertObjectToJsonBytes(holidayCalendar))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                /*.andDo(print())*/;
    	
    	verify(holidayCalendarService, times(1)).save(any(HolidayCalendar.class));
    }
    
    @Test
    public void testDelete() throws Exception {    	
   	
    	restMvc.perform(delete("/api/v1.0/holidaycalendar/1")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                /*.andDo(print())*/;
    	
    	verify(holidayCalendarService, times(1)).delete(anyLong());
    }

}
