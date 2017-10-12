import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserPickComponent } from './user-pick.component';
import { UserPickDetailComponent } from './user-pick-detail.component';
import { UserPickPopupComponent } from './user-pick-dialog.component';
import { UserPickDeletePopupComponent } from './user-pick-delete-dialog.component';

export const userPickRoute: Routes = [
    {
        path: 'user-pick',
        component: UserPickComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserPicks'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'user-pick/:id',
        component: UserPickDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserPicks'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const userPickPopupRoute: Routes = [
    {
        path: 'user-pick-new',
        component: UserPickPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserPicks'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'user-pick/:id/edit',
        component: UserPickPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserPicks'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'user-pick/:id/delete',
        component: UserPickDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserPicks'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
