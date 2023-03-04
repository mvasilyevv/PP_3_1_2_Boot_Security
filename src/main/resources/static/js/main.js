import * as configuration from "./configuration.js";

configuration.navbarConfigurator().catch((err) => {
  console.log(err);
});
