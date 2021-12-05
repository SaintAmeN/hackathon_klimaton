const {createProxyMiddleware} = require('http-proxy-middleware');

module.exports = function (app) {
    app.use(
        '/api',
        createProxyMiddleware({
            target: 'http://klimaton-server-host:9090',
            changeOrigin: true,
            ws: true
        })
    );
};
