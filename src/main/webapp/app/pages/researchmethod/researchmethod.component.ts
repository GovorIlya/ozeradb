import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
 import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
 import { Observable } from 'rxjs/Rx';

import { Researchmethod } from './researchmethod.model';
import { ResearchmethodService } from './researchmethod.service';
import { Principal } from '../../shared';

import {Factormethod, FactormethodService} from '../factormethod';
import {Unit} from '../../entities/unit';
import {UnitService} from '../../entities/unit';
import {RatingMethod} from '../../entities/rating-method';
import {RatingMethodService} from '../../entities/rating-method';

@Component({
    selector: 'jhi-researchmethod',
    templateUrl: './researchmethod.component.html'
})
export class ResearchmethodComponent implements OnInit, OnDestroy {

    factormethods: Factormethod[];
    ratingMethods:RatingMethod[];
    units: Unit[];
    unName:string;
    uResearchId: number;


    researchmethod: Researchmethod = new Researchmethod();

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
        private factorMethodService: FactormethodService,
        private ratingMethodService:RatingMethodService,
        private unitService:UnitService,
        private researchmethodService: ResearchmethodService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.factorMethodService.query().subscribe(
            (res: HttpResponse<Factormethod[]>) => {
                this.factormethods = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.ratingMethodService.query().subscribe(
            (res: HttpResponse<RatingMethod[]>) => {
                this.ratingMethods = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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

        this.registerChangeInResearchmethods();
    }

    ngOnDestroy() {

        this.eventManager.destroy(this.eventSubscriber);
    }
    registerChangeInResearchmethods() {
        this.eventSubscriber = this.eventManager.subscribe('researchmethodListModification', (response) => this.loadAll());
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
        this.uResearchId=this.units[uId].ratingMethod.id;


    }
}
