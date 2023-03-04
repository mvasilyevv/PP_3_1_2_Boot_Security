import * as configuration from "./configuration.js";

configuration.userInfoConfigurator().catch((err) => {
  console.log(err);
});
