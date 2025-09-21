package com.example;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HelloServletTest {

    private HelloServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter stringWriter;
    private PrintWriter writer;

    @BeforeEach
    public void setUp() throws Exception {
        servlet = new HelloServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
    }

    // ✅ Test case 1: Servlet sets correct content type
    @Test
    public void testContentTypeIsHtml() throws Exception {
        servlet.doGet(request, response);
        verify(response).setContentType("text/html");
    }

    // ✅ Test case 2: Servlet outputs "Hello from Java 21 WebApp!"
    @Test
    public void testHelloMessageIsPresent() throws Exception {
        servlet.doGet(request, response);
        writer.flush();
        String output = stringWriter.toString();
        assertTrue(output.contains("Hello from Java 21 WebApp!"),
                "Servlet output should contain the hello message");
    }

    // ✅ Test case 3: Servlet wraps output in <html> and </html>
    @Test
    public void testHtmlStructure() throws Exception {
        servlet.doGet(request, response);
        writer.flush();
        String output = stringWriter.toString();
        assertTrue(output.contains("<html>") && output.contains("</html>"),
                "Output should include HTML tags");
    }

    // ✅ Test case 4: Servlet includes <h2> tag for message
    @Test
    public void testHeadingTagIsPresent() throws Exception {
        servlet.doGet(request, response);
        writer.flush();
        String output = stringWriter.toString();
        assertTrue(output.contains("<h2>") && output.contains("</h2>"),
                "Output should contain the <h2> heading tag");
    }

    // ✅ Test case 5: Servlet does not return empty response
    @Test
    public void testResponseIsNotEmpty() throws Exception {
        servlet.doGet(request, response);
        writer.flush();
        String output = stringWriter.toString().trim();
        assertFalse(output.isEmpty(), "Servlet output should not be empty");
    }
}
