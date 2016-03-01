/**
 * Created by jsuch2362 on 2016. 3. 1..
 */
requirejs.config({
    "baseUrl": "/lib/js",
    "paths": {
        "jquery": "jquery/jquery",
        "bootstrap": "bootstrap/bootstrap",
        "ripple": "bootstrap-material-design/ripples",
        "material": "bootstrap-material-design/material"
    },
    "shim": {
        "bootstrap": ["jquery"],
        "ripple": ["jquery"],
        "material": ["bootstrap", "ripple"]
    }
});