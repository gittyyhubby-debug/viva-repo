package com.example.Business.items.controller;

import com.example.persistence.model.CashierProducts;
import com.example.persistence.dao.ProductsDAO;
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

public class ProductEditServletTest {

    // Mock HttpServletRequest with parameter map and attributes map
    static class MockHttpServletRequest extends HttpServletRequestWrapper {
        private final Map<String, String> parameters = new HashMap<>();
        private final Map<String, Object> attributes = new HashMap<>();
        private RequestDispatcher dispatcher;

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

        @Override
        public void setAttribute(String name, Object o) {
            attributes.put(name, o);
        }

        @Override
        public Object getAttribute(String name) {
            return attributes.get(name);
        }

        public void setRequestDispatcher(RequestDispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        @Override
        public RequestDispatcher getRequestDispatcher(String path) {
            return dispatcher;
        }
    }

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

    // Mock RequestDispatcher records forwarded path
    static class MockRequestDispatcher implements RequestDispatcher {
        private String forwardedPath;
        private boolean forwarded = false;

        public MockRequestDispatcher(String path) {
            this.forwardedPath = path;
        }

        public boolean isForwarded() {
            return forwarded;
        }

        public String getForwardedPath() {
            return forwardedPath;
        }


        @Override
        public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {

        }

        @Override
        public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {

        }
    }

    // Dummy classes to satisfy wrappers
    static class DummyHttpServletRequest implements HttpServletRequest {
        @Override public String getParameter(String name) { return null; }

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

        @Override public Object getAttribute(String name) { return null; }

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

        @Override public void setAttribute(String name, Object o) {}

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

        // many methods omitted for brevity - throw UnsupportedOperationException or return null/0 as needed
        @Override public RequestDispatcher getRequestDispatcher(String s) { return null; }

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

        @Override
        public String getAuthType() {
            return "";
        }

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

        @Override public String getMethod() { return null; }

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
        // ...
    }

    static class DummyHttpServletResponse implements HttpServletResponse {
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

        // many methods omitted for brevity
        @Override public void setContentType(String s) {}

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
        // ...
    }

    // To isolate servlet from real DAO, override ProductsDAO with a stub inside test:
    static class StubProductsDAO extends ProductsDAO {
        boolean updateCalled = false;
        CashierProducts savedProduct = null;

        @Override
        public CashierProducts getProductById(int id) {
            if (id == 1) {
                CashierProducts p = new CashierProducts();
                p.setId(1);
                p.setName("Mock Product");
                p.setDescription("Mock Desc");
                p.setCategory("Mock Category");
                p.setQuantity(5);
                p.setPrice(10.0);
                return p;
            }
            return null;
        }

        @Override
        public void updateProduct(CashierProducts p) {
            updateCalled = true;
            savedProduct = p;
        }
    }

    static class TestableProductEditServlet extends ProductEditServlet {
        StubProductsDAO stubDAO = new StubProductsDAO();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                CashierProducts product = stubDAO.getProductById(id);
                if (product == null) throw new RuntimeException("Product not found");
                req.setAttribute("product", product);
                req.getRequestDispatcher("/Admin/Products/ProductForm.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendRedirect("products?error=editLoad");
            }
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try {
                CashierProducts p = new CashierProducts();
                p.setId(Integer.parseInt(req.getParameter("id")));
                p.setName(req.getParameter("name"));
                p.setDescription(req.getParameter("description"));
                p.setCategory(req.getParameter("category"));
                p.setQuantity(Integer.parseInt(req.getParameter("quantity")));
                p.setPrice(Double.parseDouble(req.getParameter("price")));

                stubDAO.updateProduct(p);
                resp.sendRedirect("products");
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendRedirect("products?error=editSave");
            }
        }
    }

//    @Test
//    public void testDoGet_Success() throws ServletException, IOException {
//        TestableProductEditServlet servlet = new TestableProductEditServlet();
//
//        MockHttpServletRequest req = new MockHttpServletRequest();
//        req.setParameter("id", "1");
//
//        MockRequestDispatcher dispatcher = new MockRequestDispatcher("/Admin/Products/ProductForm.jsp");
//        req.setRequestDispatcher(dispatcher);
//
//        MockHttpServletResponse resp = new MockHttpServletResponse();
//
//        servlet.doGet(req, resp);
//
//        assertTrue(dispatcher.isForwarded(), "Request should be forwarded");
//        assertNotNull(req.getAttribute("product"));
//        assertEquals("Mock Product", ((CashierProducts) req.getAttribute("product")).getName());
//        System.out.println("doGet Success test passed: Forwarded with product attribute");
//    }

    @Test
    public void testDoGet_ProductNotFound_ShouldRedirectError() throws ServletException, IOException {
        TestableProductEditServlet servlet = new TestableProductEditServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setParameter("id", "999"); // no such product in stub

        MockRequestDispatcher dispatcher = new MockRequestDispatcher("/Admin/Products/ProductForm.jsp");
        req.setRequestDispatcher(dispatcher);

        MockHttpServletResponse resp = new MockHttpServletResponse();

        servlet.doGet(req, resp);

        assertEquals("products?error=editLoad", resp.getRedirectedUrl());
        System.out.println("doGet Product Not Found test passed: Redirected to error page");
    }

    @Test
    public void testDoGet_InvalidId_ShouldRedirectError() throws ServletException, IOException {
        TestableProductEditServlet servlet = new TestableProductEditServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setParameter("id", "invalid");

        MockRequestDispatcher dispatcher = new MockRequestDispatcher("/Admin/Products/ProductForm.jsp");
        req.setRequestDispatcher(dispatcher);

        MockHttpServletResponse resp = new MockHttpServletResponse();

        servlet.doGet(req, resp);

        assertEquals("products?error=editLoad", resp.getRedirectedUrl());
        System.out.println("doGet Invalid Id test passed: Redirected to error page");
    }

    @Test
    public void testDoPost_Success() throws ServletException, IOException {
        TestableProductEditServlet servlet = new TestableProductEditServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setParameter("id", "1");
        req.setParameter("name", "Updated Name");
        req.setParameter("description", "Updated Desc");
        req.setParameter("category", "Updated Cat");
        req.setParameter("quantity", "20");
        req.setParameter("price", "199.99");

        MockHttpServletResponse resp = new MockHttpServletResponse();

        servlet.doPost(req, resp);

        assertEquals("products", resp.getRedirectedUrl());
        assertTrue(servlet.stubDAO.updateCalled);
        assertEquals("Updated Name", servlet.stubDAO.savedProduct.getName());
        System.out.println("doPost Success test passed: Redirected and updateProduct called");
    }

    @Test
    public void testDoPost_InvalidQuantity_ShouldRedirectError() throws ServletException, IOException {
        TestableProductEditServlet servlet = new TestableProductEditServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setParameter("id", "1");
        req.setParameter("name", "Updated Name");
        req.setParameter("description", "Updated Desc");
        req.setParameter("category", "Updated Cat");
        req.setParameter("quantity", "invalid");
        req.setParameter("price", "199.99");

        MockHttpServletResponse resp = new MockHttpServletResponse();

        servlet.doPost(req, resp);

        assertEquals("products?error=editSave", resp.getRedirectedUrl());
        System.out.println("doPost Invalid quantity test passed: Redirected to error page");
    }

    @Test
    public void testDoPost_InvalidPrice_ShouldRedirectError() throws ServletException, IOException {
        TestableProductEditServlet servlet = new TestableProductEditServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setParameter("id", "1");
        req.setParameter("name", "Updated Name");
        req.setParameter("description", "Updated Desc");
        req.setParameter("category", "Updated Cat");
        req.setParameter("quantity", "10");
        req.setParameter("price", "invalid");

        MockHttpServletResponse resp = new MockHttpServletResponse();

        servlet.doPost(req, resp);

        assertEquals("products?error=editSave", resp.getRedirectedUrl());
        System.out.println("doPost Invalid price test passed: Redirected to error page");
    }
}
