import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ProblemtypesComponent } from './problemtypes.component';
export const ProblemtypesRoute: Routes = [
    {
        path: 'problemtypes-problemtypes',
        component: ProblemtypesComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ozeradbApp.problemtypes-problemtypes.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
];
