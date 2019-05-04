const path = require("path");
const webpack = require("webpack");
const merge = require("webpack-merge");
const glob = require('glob');

const CopyWebpackPlugin = require("copy-webpack-plugin");
const CleanWebpackPlugin = require("clean-webpack-plugin");

// to extract the css as a separate file
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

var MODE = process.env.npm_lifecycle_event === "prod" ? "production" : "development";

const elmBasePath = path.resolve(__dirname, 'src/elm');
const jsBasePath = path.resolve(__dirname, 'src');
const elmCompileFolders = ['login'];

const entries = {};

const elmTargets = glob.sync(`${elmBasePath}/+(${elmCompileFolders.join('|')})/*.elm`);
elmTargets.forEach(value => {
  const re = new RegExp(`${elmBasePath}/`);
  const key = value.replace(re, '');

  entries[key] = value;
});

const jsTargets = glob.sync(`${jsBasePath}/*.js`);
jsTargets.forEach(value => {
  const re = new RegExp(`${jsBasePath}/`);
  const key = value.replace(re, '');

  entries[key] = value;
});


// console.log(entries);

var common = {
  mode: MODE,

  entry: entries,

  // 出力の設定
  output: {
    // 出力するファイル名
    filename: '[name]',
    // 出力先のパス（絶対パスを指定する必要がある）
    path: path.join(__dirname, 'public/js')
  },

  resolve: {
    modules: [path.join(__dirname, "src"), "node_modules"],
    extensions: [".js", ".elm", ".scss", ".png"]
  },

  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: {
          loader: "babel-loader"
        }
      },
      {
        test: /\.scss$/,
        exclude: [/elm-stuff/, /node_modules/],
        loaders: ["style-loader", "css-loader", "sass-loader"]
      },
      {
        test: /\.css$/,
        exclude: [/elm-stuff/, /node_modules/],
        loaders: ["style-loader", "css-loader"]
      },
      {
        test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
        exclude: [/elm-stuff/, /node_modules/],
        loader: "url-loader",
        options: {
          limit: 10000,
          mimetype: "application/font-woff"
        }
      },
      {
        test: /\.(ttf|eot|svg)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
        exclude: [/elm-stuff/, /node_modules/],
        loader: "file-loader"
      },
      {
        test: /\.(jpe?g|png|gif|svg)$/i,
        loader: "file-loader"
      }
    ]
  },
};

if (MODE === "development") {
  console.log("Building for dev...");

  module.exports = merge(common, {
    plugins: [
      // Suggested for hot-loading
      new webpack.NamedModulesPlugin(),
      // Prevents compilation errors causing the hot loader to lose state
      new webpack.NoEmitOnErrorsPlugin()
    ],

    module: {
      rules: [
        {
          test: /\.elm$/,
          exclude: [/elm-stuff/, /node_modules/],
          use: [
            { loader: 'elm-hot-webpack-loader' },
            {
              loader: "elm-webpack-loader",
              options: {
                // add Elm's debug overlay to output
                debug: true,
                forceWatch: true,
              }
            }
          ]
        }
      ]
    },
    serve: {
      inline: true,
      stats: "errors-only",
      content: [path.join(__dirname, "src/assets")],

      devMiddleware: {
        watch:true,
        watchOptions:{
          aggregateTimeout: 300,
          poll:1000
        }
      },
    },
    //
    watch:true,
    watchOptions: {
      ignored: /node_modules/,
      aggregateTimeout: 300,
      // ５秒毎にポーリング
      poll: 5000
    },
  });
}

if (MODE === "production") {

  console.log("Building for Production...");

  module.exports = merge(common, {
    plugins: [
      // Delete everything from output directory and report to user
      new CleanWebpackPlugin(["dist"], {
        root: __dirname,
        exclude: [],
        verbose: true,
        dry: false
      }),
      new CopyWebpackPlugin([
        {
          from: "src/assets"
        }
      ]),
      new MiniCssExtractPlugin({
        // Options similar to the same options in webpackOptions.output
        // both options are optional
        filename: "[name]-[hash].css"
      })
    ],
    module: {
      rules: [
        {
          test: /\.elm$/,
          exclude: [/elm-stuff/, /node_modules/],
          use: [
            { loader: "elm-webpack-loader" }
          ]
        },
        {
          test: /\.css$/,
          exclude: [/elm-stuff/, /node_modules/],
          loaders: [MiniCssExtractPlugin.loader, "css-loader"]
        },
        {
          test: /\.scss$/,
          exclude: [/elm-stuff/, /node_modules/],
          loaders: [MiniCssExtractPlugin.loader, "css-loader", "sass-loader"]
        }
      ]
    }
  });
}
