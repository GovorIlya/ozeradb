import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { DescriptionComponent } from './description.component';
export const DescriptionRoute: Routes = [
    {
        path: 'description-description',
        component: DescriptionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ozeradbApp.description-description.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
];
