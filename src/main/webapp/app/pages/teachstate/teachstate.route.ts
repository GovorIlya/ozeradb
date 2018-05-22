import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { TeachstateComponent } from './teachstate.component';
export const TeachstateRoute: Routes = [
    {
        path: 'teachstate-teachstate',
        component: TeachstateComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ozeradbApp.teachstate-teachstate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
];
