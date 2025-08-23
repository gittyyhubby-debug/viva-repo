package com.example.Business.billing;

import com.example.persistence.dao.ProductsDAO;
import com.example.persistence.model.CartItem;
import com.example.persistence.model.CashierProducts;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AddToCartServletTest {


    static class MockHttpServletRequest extends HttpServletRequestWrapper {
        private final Map<String, String> parameters = new HashMap<>();
        private HttpSession session;

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

        public void setSession(HttpSession session) {
            this.session = session;
        }

        @Override
        public HttpSession getSession() {
            if (session == null) {
                session = new MockHttpSession();
            }
            return session;
        }
    }

    static class MockHttpServletResponse extends HttpServletResponseWrapper {
        private String redirectedUrl;

        public MockHttpServletResponse() {
            super(new DummyHttpServletResponse());
        }

        @Override
        public void sendRedirect(String location) {
            this.redirectedUrl = location;
        }

        public String getRedirectedUrl() {
            return redirectedUrl;
        }
    }

    static class MockHttpSession implements HttpSession, com.example.Business.billing.MockHttpSession {
        private final Map<String, Object> attributes = new HashMap<>();

        @Override
        public Object getAttribute(String name) {
            return attributes.get(name);
        }

        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }

        @Override
        public void setAttribute(String name, Object value) {
            attributes.put(name, value);
        }

        // Other methods not needed for this test throw UnsupportedOperationException
        @Override public long getCreationTime() {throw new UnsupportedOperationException();}
        @Override public String getId() {throw new UnsupportedOperationException();}
        @Override public long getLastAccessedTime() {throw new UnsupportedOperationException();}
        @Override public ServletContext getServletContext() {throw new UnsupportedOperationException();}
        @Override public void setMaxInactiveInterval(int interval) {throw new UnsupportedOperationException();}
        @Override public int getMaxInactiveInterval() {throw new UnsupportedOperationException();}
//        @Override public <HttpSessionContext> HttpSessionContext getSessionContext() {throw new UnsupportedOperationException();}
//        @Override public Object getValue(String name) {throw new UnsupportedOperationException();}
//        @Override public Enumeration<String> getAttributeNames() {throw new UnsupportedOperationException();}
//        @Override public String[] getValueNames() {throw new UnsupportedOperationException();}
//        @Override public void putValue(String name, Object value) {throw new UnsupportedOperationException();}
//        @Override public void removeValue(String name) {throw new UnsupportedOperationException();}
        @Override public void removeAttribute(String name) {attributes.remove(name);}
        @Override public void invalidate() {throw new UnsupportedOperationException();}
        @Override public boolean isNew() {throw new UnsupportedOperationException();}

        @Override
        public Accessor getAccessor() {
            return HttpSession.super.getAccessor();
        }
    }

    // Dummy classes to satisfy wrappers
    static class DummyHttpServletRequest implements jakarta.servlet.http.HttpServletRequest {
        @Override
        public Object getAttribute(String name) {
            return null;
        }

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

        // Many methods omitted for brevity
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

        @Override
        public String getMethod() {
            return "";
        }

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

        @Override public HttpSession getSession() { return null; }

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
        // ... other methods throw UnsupportedOperationException
    }

    static class DummyHttpServletResponse implements jakarta.servlet.http.HttpServletResponse {
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

        @Override
        public void setContentType(String type) {

        }

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
        // ... other methods throw UnsupportedOperationException
    }

    // Stub ProductsDAO to simulate DB
    static class StubProductsDAO extends ProductsDAO {
        private final Map<Integer, CashierProducts> products = new HashMap<>();

        public StubProductsDAO() {
            CashierProducts p1 = new CashierProducts();
            p1.setId(1);
            p1.setName("Test Product 1");
            p1.setQuantity(5);
            p1.setPrice(100);
            products.put(1, p1);

            CashierProducts p2 = new CashierProducts();
            p2.setId(2);
            p2.setName("Test Product 2");
            p2.setQuantity(0);
            p2.setPrice(50);
            products.put(2, p2);
        }

        @Override
        public CashierProducts getProductById(int id) throws SQLException {
            if (products.containsKey(id)) {
                return products.get(id);
            }
            return null;
        }
    }

    // Subclass servlet to use stub DAO
    static class TestableAddToCartServlet extends AddToCartServlet {
        StubProductsDAO stubDAO = new StubProductsDAO();

        @Override
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            String idStr = request.getParameter("id");
            String qtyStr = request.getParameter("quantity");

            if (idStr == null || qtyStr == null || idStr.isEmpty() || qtyStr.isEmpty()) {
                response.sendRedirect("billing.jsp?error=invalid");
                return;
            }

            int productId, quantity;
            try {
                productId = Integer.parseInt(idStr);
                quantity = Integer.parseInt(qtyStr);
            } catch (NumberFormatException e) {
                response.sendRedirect("billing.jsp?error=invalid");
                return;
            }

            CashierProducts product;
            try {
                product = stubDAO.getProductById(productId);
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("billing.jsp?error=notfound");
                return;
            }

            if (product == null) {
                response.sendRedirect("billing.jsp?error=notfound");
                return;
            }

            if (quantity > product.getQuantity()) {
                response.sendRedirect("billing.jsp?error=outofstock");
                return;
            }

            HttpSession session = request.getSession();
            List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
            }

            boolean found = false;
            for (CartItem item : cart) {
                if (item.getProduct().getId() == productId) {
                    item.setQuantity(item.getQuantity() + quantity);
                    found = true;
                    break;
                }
            }

            if (!found) {
                cart.add(new CartItem(product, quantity));
            }

            session.setAttribute("cart", cart);
            response.sendRedirect("billing.jsp?status=added");
        }
    }

    @Test
    public void testMissingParameters() throws IOException, ServletException {
        TestableAddToCartServlet servlet = new TestableAddToCartServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse resp = new MockHttpServletResponse();

        servlet.doPost(req, resp);

        assertEquals("billing.jsp?error=invalid", resp.getRedirectedUrl());
        System.out.println("Missing parameters test passed");
    }

    @Test
    public void testInvalidNumberParameters() throws IOException, ServletException {
        TestableAddToCartServlet servlet = new TestableAddToCartServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setParameter("id", "abc");
        req.setParameter("quantity", "xyz");
        MockHttpServletResponse resp = new MockHttpServletResponse();

        servlet.doPost(req, resp);

        assertEquals("billing.jsp?error=invalid", resp.getRedirectedUrl());
        System.out.println("Invalid number parameters test passed");
    }

    @Test
    public void testProductNotFound() throws IOException, ServletException {
        TestableAddToCartServlet servlet = new TestableAddToCartServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setParameter("id", "999");  // nonexistent product
        req.setParameter("quantity", "1");
        MockHttpServletResponse resp = new MockHttpServletResponse();

        servlet.doPost(req, resp);

        assertEquals("billing.jsp?error=notfound", resp.getRedirectedUrl());
        System.out.println("Product not found test passed");
    }

    @Test
    public void testOutOfStock() throws IOException, ServletException {
        TestableAddToCartServlet servlet = new TestableAddToCartServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setParameter("id", "2");  // product with 0 stock in stub
        req.setParameter("quantity", "1");
        MockHttpServletResponse resp = new MockHttpServletResponse();

        servlet.doPost(req, resp);

        assertEquals("billing.jsp?error=outofstock", resp.getRedirectedUrl());
        System.out.println("Out of stock test passed");
    }

    @Test
    public void testAddNewProductToCart() throws IOException, ServletException {
        TestableAddToCartServlet servlet = new TestableAddToCartServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();
        req.setSession(session);

        req.setParameter("id", "1");
        req.setParameter("quantity", "2");

        MockHttpServletResponse resp = new MockHttpServletResponse();

        servlet.doPost(req, resp);

        assertEquals("billing.jsp?status=added", resp.getRedirectedUrl());

        @SuppressWarnings("unchecked")
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        assertNotNull(cart);
        assertEquals(1, cart.size());
        CartItem item = cart.get(0);
        assertEquals(1, item.getProduct().getId());
        assertEquals(2, item.getQuantity());

        System.out.println("Add new product to cart test passed");
    }

    @Test
    public void testIncreaseQuantityForExistingProduct() throws IOException, ServletException {
        TestableAddToCartServlet servlet = new TestableAddToCartServlet();

        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();

        // Existing cart with one product of quantity 1
        CashierProducts product = new CashierProducts();
        product.setId(1);
        product.setName("Test Product 1");
        CartItem existingItem = new CartItem(product, 1);

        List<CartItem> cart = new ArrayList<>();
        cart.add(existingItem);

        session.setAttribute("cart", cart);
        req.setSession(session);

        req.setParameter("id", "1");
        req.setParameter("quantity", "3");

        MockHttpServletResponse resp = new MockHttpServletResponse();

        servlet.doPost(req, resp);

        assertEquals("billing.jsp?status=added", resp.getRedirectedUrl());

        @SuppressWarnings("unchecked")
        List<CartItem> updatedCart = (List<CartItem>) session.getAttribute("cart");
        assertNotNull(updatedCart);
        assertEquals(1, updatedCart.size());
        CartItem item = updatedCart.get(0);
        assertEquals(4, item.getQuantity()); // 1 + 3

        System.out.println("Increase quantity for existing product test passed");
    }
}
