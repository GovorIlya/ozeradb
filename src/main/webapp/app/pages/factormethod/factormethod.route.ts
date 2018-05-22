import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { FactormethodComponent } from './factormethod.component';
export const FactormethodRoute: Routes = [
    {
        path: 'factormethod-factormethod',
        component: FactormethodComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ozeradbApp.factormethod-factormethod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
];
