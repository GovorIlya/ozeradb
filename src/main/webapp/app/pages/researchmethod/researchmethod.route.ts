import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ResearchmethodComponent } from './researchmethod.component';
export const ResearchmethodRoute: Routes = [
    {
        path: 'researchmethod-researchmethod',
        component: ResearchmethodComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ozeradbApp.researchmethod-researchmethod.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
];
