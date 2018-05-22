import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
 import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { Observable } from 'rxjs/Rx';

import { Description } from './description.model';
import { DescriptionService } from './description.service';
import { Principal } from '../../shared';
import {UnitDescriptionService} from '../../entities/unit-description';
import {UnitDescription} from '../../entities/unit-description';
import {Unit} from '../../entities/unit';
import {UnitService} from '../../entities/unit';

@Component({
    selector: 'jhi-description',
    templateUrl: './description.component.html'
})
export class DescriptionComponent implements OnInit, OnDestroy {

    unitDescriptions: UnitDescription[];
    units: Unit[];
    unName: string;
    createYear: string;
    square: string;
    collectors: string;
    prst: number;
    sbros: string;

    description: Description = new Description();

    currentAccount: any;
    eventSubscriber: Subscription;
    isSaving: Boolean;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    constructor(
        private unitDescriptionService: UnitDescriptionService,
        private unitService: UnitService,

        private descriptionService: DescriptionService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        // load descriptions
        this.unitDescriptionService.query().subscribe(
            (res: HttpResponse<UnitDescription[]>) => {
                this.unitDescriptions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        // load units
        this.unitService.query().subscribe(
            (res: HttpResponse<Unit[]>) => {
                this.units = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {

        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });

        this.registerChangeInDescriptions();
    }

    ngOnDestroy() {

        this.eventManager.destroy(this.eventSubscriber);
    }
    registerChangeInDescriptions() {
        this.eventSubscriber = this.eventManager.subscribe('descriptionListModification', (response) => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
    onClickMe(uId:any) {
        this.unName=this.units[uId].unitName;
        this.createYear=this.unitDescriptions[uId].createYear;
        this.square=this.unitDescriptions[uId].square;
        this.collectors=this.unitDescriptions[uId].collesctors;
        this.prst=this.unitDescriptions[uId].prst;
        this.sbros=this.unitDescriptions[uId].sbros;
    }
}
