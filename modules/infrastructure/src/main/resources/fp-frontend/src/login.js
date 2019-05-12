'use strict';

const {Elm} = require('./elm/login/Login.elm');
const mountNode = document.getElementById('elm');
const app = Elm.Login.init({node: mountNode});
