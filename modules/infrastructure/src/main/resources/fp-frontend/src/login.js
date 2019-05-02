'use strict';

const {Elm} = require('./elm/login/login.elm');
const mountNode = document.getElementById('elm');
const app = Elm.Main.init({node: mountNode});
