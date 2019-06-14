'use strict';

const {Elm} = require('./elm/System/System.elm');
const mountNode = document.getElementById('elm');
const app = Elm.System.System.init({node: mountNode});
