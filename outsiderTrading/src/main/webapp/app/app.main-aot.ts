import { platformBrowser } from '@angular/platform-browser';
import { ProdConfig } from './blocks/config/prod.config';
import { OutsiderTradingAppModuleNgFactory } from '../../../../build/aot/src/main/webapp/app/app.module.ngfactory';

ProdConfig();

platformBrowser().bootstrapModuleFactory(OutsiderTradingAppModuleNgFactory)
.then((success) => console.log(`Application started`))
.catch((err) => console.error(err));
