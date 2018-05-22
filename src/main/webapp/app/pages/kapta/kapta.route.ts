import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { KaptaComponent } from './kapta.component';
export const KaptaRoute: Routes = [
    {
        path: 'kapta-kapta',
        component: KaptaComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ozeradbApp.kapta-kapta.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
];
