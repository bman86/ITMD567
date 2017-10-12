import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OutsiderTradingSharedModule } from '../../shared';
import { OutsiderTradingAdminModule } from '../../admin/admin.module';
import {
    UserPickService,
    UserPickPopupService,
    UserPickComponent,
    UserPickDetailComponent,
    UserPickDialogComponent,
    UserPickPopupComponent,
    UserPickDeletePopupComponent,
    UserPickDeleteDialogComponent,
    userPickRoute,
    userPickPopupRoute,
} from './';

const ENTITY_STATES = [
    ...userPickRoute,
    ...userPickPopupRoute,
];

@NgModule({
    imports: [
        OutsiderTradingSharedModule,
        OutsiderTradingAdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        UserPickComponent,
        UserPickDetailComponent,
        UserPickDialogComponent,
        UserPickDeleteDialogComponent,
        UserPickPopupComponent,
        UserPickDeletePopupComponent,
    ],
    entryComponents: [
        UserPickComponent,
        UserPickDialogComponent,
        UserPickPopupComponent,
        UserPickDeleteDialogComponent,
        UserPickDeletePopupComponent,
    ],
    providers: [
        UserPickService,
        UserPickPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OutsiderTradingUserPickModule {}
