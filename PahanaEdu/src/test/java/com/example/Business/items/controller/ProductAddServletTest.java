package com.example.Business.items.controller;

import com.example.persistence.dao.ProductsDAO;
import com.example.persistence.model.CashierProducts;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProductAddServletTest {

    static class MockHttpServletRequest extends HttpServletRequestWrapper {
        private final Map<String, String> parameters = new HashMap<>();

        public MockHttpServletRequest() {
            super(new DummyHttpServletRequest());
        }

        public void setParameter(String name, String value) {
            parameters.put(name, value);
        }

        @Override
        public String getParameter(String name) {
            return parameters.get(name);
        }
    }

    static class DummyHttpServletRequest extends HttpServletRequestAdapter {}

    static class MockHttpServletResponse extends HttpServletResponseWrapper {
        private String redirectedUrl;

        public MockHttpServletResponse() {
            super(new DummyHttpServletResponse());
        }

        @Override
        public void sendRedirect(String location) throws IOException {
            this.redirectedUrl = location;
        }

        public String getRedirectedUrl() {
            return redirectedUrl;
        }
    }

    static class DummyHttpServletResponse extends HttpServletResponseAdapter {}

    // Dummy adapter classes because HttpServletRequest and Response are interfaces
    static class HttpServletRequestAdapter implements HttpServletRequest {
        // Implement only required methods with default behavior or empty
        // For brevity, not implementing all methods, only getParameter is overridden in subclass.
        public String getParameter(String name) { return null; }

        @Override
        public Enumeration<String> getParameterNames() {
            return null;
        }

        @Override
        public String[] getParameterValues(String name) {
            return new String[0];
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return Map.of();
        }

        @Override
        public String getProtocol() {
            return "";
        }

        @Override
        public String getScheme() {
            return "";
        }

        @Override
        public String getServerName() {
            return "";
        }

        @Override
        public int getServerPort() {
            return 0;
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return null;
        }

        @Override
        public String getRemoteAddr() {
            return "";
        }

        @Override
        public String getRemoteHost() {
            return "";
        }

        @Override
        public void setAttribute(String name, Object o) {

        }

        @Override
        public void removeAttribute(String name) {

        }

        @Override
        public Locale getLocale() {
            return null;
        }

        @Override
        public Enumeration<Locale> getLocales() {
            return null;
        }

        @Override
        public boolean isSecure() {
            return false;
        }

        @Override
        public RequestDispatcher getRequestDispatcher(String path) {
            return null;
        }

        @Override
        public int getRemotePort() {
            return 0;
        }

        @Override
        public String getLocalName() {
            return "";
        }

        @Override
        public String getLocalAddr() {
            return "";
        }

        @Override
        public int getLocalPort() {
            return 0;
        }

        @Override
        public ServletContext getServletContext() {
            return null;
        }

        @Override
        public AsyncContext startAsync() throws IllegalStateException {
            return null;
        }

        @Override
        public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
            return null;
        }

        @Override
        public boolean isAsyncStarted() {
            return false;
        }

        @Override
        public boolean isAsyncSupported() {
            return false;
        }

        @Override
        public AsyncContext getAsyncContext() {
            return null;
        }

        @Override
        public DispatcherType getDispatcherType() {
            return null;
        }

        @Override
        public String getRequestId() {
            return "";
        }

        @Override
        public String getProtocolRequestId() {
            return "";
        }

        @Override
        public ServletConnection getServletConnection() {
            return null;
        }

        // ... other methods throw UnsupportedOperationException or return defaults
        public Object getAttribute(String s) {return null;}

        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }

        @Override
        public String getCharacterEncoding() {
            return "";
        }

        @Override
        public void setCharacterEncoding(String encoding) throws UnsupportedEncodingException {

        }

        @Override
        public int getContentLength() {
            return 0;
        }

        @Override
        public long getContentLengthLong() {
            return 0;
        }

        @Override
        public String getContentType() {
            return "";
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            return null;
        }

        public String getAuthType() {return null;}

        @Override
        public Cookie[] getCookies() {
            return new Cookie[0];
        }

        @Override
        public long getDateHeader(String name) {
            return 0;
        }

        @Override
        public String getHeader(String name) {
            return "";
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            return null;
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            return null;
        }

        @Override
        public int getIntHeader(String name) {
            return 0;
        }

        // etc.
        // To keep it short, you can add methods as needed for compilation or tests
        // but here, only getParameter is used in the servlet
        // All others throw UnsupportedOperationException:
        @Override public String getMethod() { throw new UnsupportedOperationException(); }

        @Override
        public String getPathInfo() {
            return "";
        }

        @Override
        public String getPathTranslated() {
            return "";
        }

        @Override
        public String getContextPath() {
            return "";
        }

        @Override
        public String getQueryString() {
            return "";
        }

        @Override
        public String getRemoteUser() {
            return "";
        }

        @Override
        public boolean isUserInRole(String role) {
            return false;
        }

        @Override
        public Principal getUserPrincipal() {
            return null;
        }

        @Override
        public String getRequestedSessionId() {
            return "";
        }

        @Override
        public String getRequestURI() {
            return "";
        }

        @Override
        public StringBuffer getRequestURL() {
            return null;
        }

        @Override
        public String getServletPath() {
            return "";
        }

        @Override
        public HttpSession getSession(boolean create) {
            return null;
        }

        @Override
        public HttpSession getSession() {
            return null;
        }

        @Override
        public String changeSessionId() {
            return "";
        }

        @Override
        public boolean isRequestedSessionIdValid() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromCookie() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromURL() {
            return false;
        }

        @Override
        public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
            return false;
        }

        @Override
        public void login(String username, String password) throws ServletException {

        }

        @Override
        public void logout() throws ServletException {

        }

        @Override
        public Collection<Part> getParts() throws IOException, ServletException {
            return List.of();
        }

        @Override
        public Part getPart(String name) throws IOException, ServletException {
            return null;
        }

        @Override
        public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException {
            return null;
        }
        // (implement other methods similarly or leave unimplemented for your use)
        // For brevity, left unimplemented because only getParameter is used
        // Use your IDE to generate or add minimal methods if needed
        // In practice, use Mockito or a testing framework for this.
        // Or extend from existing mocks.
        // Here, we keep it minimal.
    }

    static class HttpServletResponseAdapter implements HttpServletResponse {
        @Override
        public void addCookie(Cookie cookie) {

        }

        @Override
        public boolean containsHeader(String name) {
            return false;
        }

        @Override
        public String encodeURL(String url) {
            return "";
        }

        @Override
        public String encodeRedirectURL(String url) {
            return "";
        }

        @Override
        public void sendError(int sc, String msg) throws IOException {

        }

        @Override
        public void sendError(int sc) throws IOException {

        }

        // Same approach as HttpServletRequestAdapter for minimal stub
        @Override public void sendRedirect(String location) throws IOException {}

        @Override
        public void sendRedirect(String location, int sc, boolean clearBuffer) throws IOException {

        }

        @Override
        public void setDateHeader(String name, long date) {

        }

        @Override
        public void addDateHeader(String name, long date) {

        }

        @Override
        public void setHeader(String name, String value) {

        }

        @Override
        public void addHeader(String name, String value) {

        }

        @Override
        public void setIntHeader(String name, int value) {

        }

        @Override
        public void addIntHeader(String name, int value) {

        }

        @Override
        public void setStatus(int sc) {

        }

        @Override
        public int getStatus() {
            return 0;
        }

        @Override
        public String getHeader(String name) {
            return "";
        }

        @Override
        public Collection<String> getHeaders(String name) {
            return List.of();
        }

        @Override
        public Collection<String> getHeaderNames() {
            return List.of();
        }

        @Override
        public String getCharacterEncoding() {
            return "";
        }

        @Override
        public String getContentType() {
            return "";
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return null;
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return null;
        }

        @Override
        public void setCharacterEncoding(String encoding) {

        }

        @Override
        public void setContentLength(int len) {

        }

        @Override
        public void setContentLengthLong(long len) {

        }

        // Other methods throw UnsupportedOperationException
        @Override public void setContentType(String type) { throw new UnsupportedOperationException(); }

        @Override
        public void setBufferSize(int size) {

        }

        @Override
        public int getBufferSize() {
            return 0;
        }

        @Override
        public void flushBuffer() throws IOException {

        }

        @Override
        public void resetBuffer() {

        }

        @Override
        public boolean isCommitted() {
            return false;
        }

        @Override
        public void reset() {

        }

        @Override
        public void setLocale(Locale loc) {

        }

        @Override
        public Locale getLocale() {
            return null;
        }
        // Add others if needed
    }


//    @Test
//    public void testDoPost_Success() throws IOException {
//        ProductAddServlet servlet = new ProductAddServlet();
//
//        MockHttpServletRequest req = new MockHttpServletRequest();
//        req.setParameter("name", "Test Product");
//        req.setParameter("description", "Test Description");
//        req.setParameter("category", "Test Category");
//        req.setParameter("quantity", "10");
//        req.setParameter("price", "99.99");
//
//        MockHttpServletResponse resp = new MockHttpServletResponse();
//
//        servlet.doPost(req, resp);
//
//        assertEquals("products", resp.getRedirectedUrl());
//        System.out.println("Test Success Scenario: Passed - Redirected to 'products'");
//    }

    @Test
    public void testDoPost_MissingQuantity_ShouldRedirectError() throws IOException {
        ProductAddServlet servlet = new ProductAddServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setParameter("name", "Test Product");
        req.setParameter("description", "Test Description");
        req.setParameter("category", "Test Category");
        req.setParameter("quantity", "");  // Invalid integer
        req.setParameter("price", "99.99");

        MockHttpServletResponse resp = new MockHttpServletResponse();

        servlet.doPost(req, resp);

        assertEquals("ProductForm.jsp?error=add", resp.getRedirectedUrl());
        System.out.println("Test Error Scenario (Invalid quantity): Passed - Redirected to error page");
    }

    @Test
    public void testDoPost_InvalidPrice_ShouldRedirectError() throws IOException {
        ProductAddServlet servlet = new ProductAddServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setParameter("name", "Test Product");
        req.setParameter("description", "Test Description");
        req.setParameter("category", "Test Category");
        req.setParameter("quantity", "5");
        req.setParameter("price", "abc");  // Invalid double

        MockHttpServletResponse resp = new MockHttpServletResponse();

        servlet.doPost(req, resp);

        assertEquals("ProductForm.jsp?error=add", resp.getRedirectedUrl());
        System.out.println("Test Error Scenario (Invalid price): Passed - Redirected to error page");
    }

}
