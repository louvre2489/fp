'use strict';

const {Elm} = require('./elm/login/Login.elm');
const mountNode = document.getElementById('elm');
const app = Elm.Login.init({node: mountNode});

app.ports.portSetLocalStorage.subscribe(function(data) {
  localStorage.setItem('fp-token', data)
});
