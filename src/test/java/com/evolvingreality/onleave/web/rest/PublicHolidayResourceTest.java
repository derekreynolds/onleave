package com.evolvingreality.onleave.web.rest;

import com.evolvingreality.onleave.model.HolidayCalendar;
import com.evolvingreality.onleave.model.PublicHoliday;
import com.evolvingreality.onleave.service.HolidayCalendarService;
import com.evolvingreality.onleave.service.HolidayCalendarTestUtil;
import com.evolvingreality.onleave.service.PublicHolidayService;
import com.evolvingreality.onleave.service.PublicHolidayTestUtil;
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
 * Test class for the PublicHolidayResource REST controller.
 *
 * @see PublicHolidayResource
 */
public class PublicHolidayResourceTest {

    @Mock
    private PublicHolidayService publicHolidayService;

    @Mock
    private HolidayCalendarService holidayCalendarService;
    
    @Mock
    private UserService userService;
    
    private MockMvc restMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        PublicHolidayResource publicHolidayResource = new PublicHolidayResource(publicHolidayService, holidayCalendarService);

        this.restMvc = MockMvcBuilders.standaloneSetup(publicHolidayResource)
        		.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();

    }

    @Test
    public void testGetAll() throws Exception {
    	
    	PublicHoliday publicHoliday1 = PublicHolidayTestUtil.create();
    	PublicHoliday publicHoliday2 = PublicHolidayTestUtil.create();
    	
    	List<PublicHoliday> publicHolidays = new ArrayList<>();
    	publicHolidays.add(publicHoliday1);
    	publicHolidays.add(publicHoliday2);
    	
    	Page<PublicHoliday> page = new PageImpl<>(publicHolidays);
    	
    	doReturn(page).when(publicHolidayService).getPage(any());
    	
    	restMvc.perform(get("/api/v1.0/publicholiday")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(2))
                /*.andDo(print())*/;
    }
    
    @Test
    public void testGetOne() throws Exception {
    	
    	Optional<PublicHoliday> publicHoliday = Optional.of(PublicHolidayTestUtil.create());
    	    	    	
    	doReturn(publicHoliday).when(publicHolidayService).get(1L);
    	  	
    	restMvc.perform(get("/api/v1.0/publicholiday/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Public Holiday"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                /*.andDo(print())*/;
    }
    
    @Test
    public void testPost() throws Exception {
    	
    	PublicHoliday publicHoliday = PublicHolidayTestUtil.create();
   	
    	HolidayCalendar h = HolidayCalendarTestUtil.create(); 
    	
    	Optional<HolidayCalendar> holidayCalendar = Optional.of(h);
    	
    	doReturn(holidayCalendar).when(holidayCalendarService).get(1L);
    	
    	doReturn(Boolean.FALSE).when(publicHolidayService).hasHolidayCalendarPublicHolidayForDate(any(), any());
    	
    	doAnswer(new Answer<PublicHoliday>() {
            public PublicHoliday answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                if (args[0] instanceof PublicHoliday)
                	((PublicHoliday)args[0]).setId(1L);
                
                return null;
            }
        }).when(publicHolidayService).save(any(PublicHoliday.class));    	
    	
    	restMvc.perform(post("/api/v1.0/holidaycalendar/1/publicholiday")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
    			.content(TestUtil.convertObjectToJsonBytes(publicHoliday))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/v1.0/publicholiday/1"))
                /*.andDo(print())*/;
    	
    	verify(publicHolidayService, times(1)).save(any(PublicHoliday.class));
    }
    
    @Test
    public void testPostConflict() throws Exception {
    	
    	PublicHoliday publicHoliday = PublicHolidayTestUtil.create();
    	    
    	HolidayCalendar h = HolidayCalendarTestUtil.create(); 
    	
    	Optional<HolidayCalendar> holidayCalendar = Optional.of(h);
    	
    	doReturn(holidayCalendar).when(holidayCalendarService).get(1L);
    	
    	doReturn(Boolean.TRUE).when(publicHolidayService).hasHolidayCalendarPublicHolidayForDate(any(), any());
    	
    	restMvc.perform(post("/api/v1.0/holidaycalendar/1/publicholiday")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
    			.content(TestUtil.convertObjectToJsonBytes(publicHoliday))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                /*.andDo(print())*/;
    }
    
    @Test
    public void testPut() throws Exception {
    	
    	PublicHoliday publicHoliday = PublicHolidayTestUtil.create();    	    
   	
    	restMvc.perform(put("/api/v1.0/publicholiday")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
    			.content(TestUtil.convertObjectToJsonBytes(publicHoliday))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                /*.andDo(print())*/;
    	
    	verify(publicHolidayService, times(1)).save(any(PublicHoliday.class));
    }
    
    @Test
    public void testDelete() throws Exception {
    	
     	restMvc.perform(delete("/api/v1.0/publicholiday/1")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                /*.andDo(print())*/;
    	
    	verify(publicHolidayService, times(1)).delete(anyLong());
    }

}

